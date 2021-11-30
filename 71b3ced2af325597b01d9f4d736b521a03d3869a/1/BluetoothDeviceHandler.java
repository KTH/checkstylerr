package org.sputnikdev.esh.binding.bluetooth.handler;

import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.builder.ThingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sputnikdev.bluetooth.manager.BluetoothSmartDeviceListener;
import org.sputnikdev.bluetooth.manager.DeviceGovernor;
import org.sputnikdev.bluetooth.manager.GattService;
import org.sputnikdev.esh.binding.bluetooth.BluetoothBindingConstants;
import org.sputnikdev.esh.binding.bluetooth.internal.BluetoothHandlerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @author Vlad Kolotov - Initial contribution
 */
public class BluetoothDeviceHandler extends GenericBluetoothDeviceHandler
        implements BluetoothSmartDeviceListener {

    private Logger logger = LoggerFactory.getLogger(BluetoothDeviceHandler.class);
    private boolean initialConnectionControl;

    private final BooleanTypeChannelHandler connectedHandler = new BooleanTypeChannelHandler(
            BluetoothDeviceHandler.this, BluetoothBindingConstants.CHANNEL_CONNECTED) {
        @Override Boolean getValue() {
            return getGovernor().isReady() && getGovernor().isConnected();
        }
    };

    private final SingleChannelHandler<Boolean, OnOffType> connectionControlHandler = new BooleanTypeChannelHandler(
            BluetoothDeviceHandler.this, BluetoothBindingConstants.CHANNEL_CONNECTION_CONTROL, true) {
        @Override Boolean getValue() {
            return getGovernor().getConnectionControl();
        }
        @Override void updateThing(Boolean value) {
            getGovernor().setConnectionControl(value);
        }
        @Override Boolean getDefaultValue() {
            return initialConnectionControl;
        }
    };

    private final BluetoothChannelBuilder channelBuilder;


    public BluetoothDeviceHandler(BluetoothHandlerFactory factory, Thing thing) {
        super(factory, thing);
        addChannelHandler(connectedHandler);
        addChannelHandler(connectionControlHandler);
        channelBuilder = new BluetoothChannelBuilder(this);
    }

    @Override
    public void initialize() {
        super.initialize();
        getGovernor().addBluetoothSmartDeviceListener(this);

        updateStatus(ThingStatus.ONLINE);
    }

    @Override
    public void dispose() {
        DeviceGovernor deviceGovernor = getGovernor();
        deviceGovernor.removeBluetoothSmartDeviceListener(this);
        deviceGovernor.setConnectionControl(false);
        super.dispose();
    }

    @Override
    public void connected() {
        connectedHandler.updateChannel(true);
    }
    @Override
    public void disconnected() {
        connectedHandler.updateChannel(false);
    }

    @Override
    public void servicesResolved(List<GattService> gattServices) {
        ThingBuilder builder = editThing();

        logger.info("Building channels for services: {}", gattServices.size());
        Map<MultiChannelHandler, List<Channel>> channels = channelBuilder.buildChannels(gattServices);

        for (Map.Entry<MultiChannelHandler, List<Channel>> entry : channels.entrySet()) {
            ChannelHandler channelHandler = entry.getKey();
            addChannelHandler(channelHandler);
            updateChannels(builder, entry.getValue());
        }

        logger.info("Updating the thing with new channels");
        updateThing(builder.build());

        for (MultiChannelHandler channelHandler : channels.keySet()) {
            try {
                channelHandler.initChannels();
            } catch (Exception ex) {
                logger.error("Could not update channel handler: {}", channelHandler.getURL(), ex);
            }
        }
    }

    @Override
    public void servicesUnresolved() { }

    public boolean isInitialConnectionControl() {
        return initialConnectionControl;
    }

    public void setInitialConnectionControl(boolean initialConnectionControl) {
        this.initialConnectionControl = initialConnectionControl;
    }

    private void updateChannels(ThingBuilder builder, Collection<Channel> channels) {
        for (Channel channel : channels) {
            if (getThing().getChannel(channel.getUID().getIdWithoutGroup()) == null) {
                builder.withChannel(channel);
            }
        }
    }

}
