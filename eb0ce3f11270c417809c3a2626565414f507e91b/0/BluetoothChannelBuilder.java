package org.sputnikdev.esh.binding.bluetooth.handler;

import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.builder.ChannelBuilder;
import org.eclipse.smarthome.core.thing.type.ChannelTypeUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sputnikdev.bluetooth.URL;
import org.sputnikdev.bluetooth.gattparser.BluetoothGattParser;
import org.sputnikdev.bluetooth.gattparser.spec.Characteristic;
import org.sputnikdev.bluetooth.gattparser.spec.Field;
import org.sputnikdev.bluetooth.manager.GattCharacteristic;
import org.sputnikdev.bluetooth.manager.GattService;
import org.sputnikdev.bluetooth.manager.transport.CharacteristicAccessType;
import org.sputnikdev.esh.binding.bluetooth.BluetoothBindingConstants;
import org.sputnikdev.esh.binding.bluetooth.internal.BluetoothUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Vlad Kolotov
 */
class BluetoothChannelBuilder {

    private Logger logger = LoggerFactory.getLogger(BluetoothChannelBuilder.class);

    private final BluetoothHandler handler;
    private Set<String> advancedServices;

    BluetoothChannelBuilder(BluetoothHandler handler) {
        this.handler = handler;
    }

    BluetoothChannelBuilder withAdvancedServices(List<String> services) {
        advancedServices = services != null
                ? services.stream().map(String::toLowerCase).collect(Collectors.toSet()) : null;
        return this;
    }

    protected Map<MultiChannelHandler, List<Channel>> buildChannels(List<GattService> services) {
        BluetoothGattParser gattParser = handler.getGattParser();
        Map<MultiChannelHandler, List<Channel>> result = new HashMap<>();
        for (GattService service : services) {
            for (GattCharacteristic characteristic : service.getCharacteristics()) {
                logger.info("Handling a new characteristic: {}", characteristic.getURL());

                if (!gattParser.isKnownCharacteristic(characteristic.getURL().getCharacteristicUUID())) {
                    logger.info("Skipping unknown characteristic: {} ", characteristic.getURL());
                    continue;
                }

                Set<CharacteristicAccessType> flags = characteristic.getFlags();
                boolean readAccess = BluetoothUtils.hasReadAccess(flags)
                        || BluetoothUtils.hasNotificationAccess(flags);
                boolean writeAccess = BluetoothUtils.hasWriteAccess(flags);
                if (!readAccess && !writeAccess) {
                    logger.info("The characteristic {} is not supported, flags: {} ",
                            characteristic.getURL(), characteristic.getFlags());
                    continue;
                }
                if (readAccess && gattParser.isValidForRead(characteristic.getURL().getCharacteristicUUID())
                        || writeAccess) {
                    logger.info("Creating channels for characteristic: {}", characteristic.getURL());
                    result.put(new MultiChannelHandler(handler, characteristic.getURL(), flags),
                            buildChannels(handler.getThing(), service, characteristic));
                } else {
                    logger.info("Skipping invalid characteristic {}.", characteristic.getURL());
                }
            }
        }
        return result;
    }

    private List<Channel> buildChannels(Thing thing, GattService service, GattCharacteristic characteristic) {
        List<Channel> channels = new ArrayList<>();
        for (Field field : handler.getGattParser().getFields(characteristic.getURL().getCharacteristicUUID())) {
            Channel channel = buildChannel(thing, service, characteristic, field);
            channels.add(channel);
        }
        return channels;
    }

    private Channel buildChannel(Thing thing, GattService service, GattCharacteristic characteristic, Field field) {
        URL channelURL = characteristic.getURL().copyWithField(field.getName());
        ChannelUID channelUID = new ChannelUID(thing.getUID(), BluetoothUtils.getChannelUID(channelURL));

        String channelType = getChannelType(service, characteristic, field);

        ChannelTypeUID channelTypeUID = new ChannelTypeUID(BluetoothBindingConstants.BINDING_ID, channelType);
        return ChannelBuilder.create(channelUID, getAcceptedItemType(field))
                .withType(channelTypeUID)
                .withProperties(getFieldProperties(field))
                .withLabel(getChannelLabel(characteristic.getURL().getCharacteristicUUID(), field))
                .build();
    }

    private String getChannelType(GattService service, GattCharacteristic characteristic, Field field) {
        boolean advanced = advancedServices != null && advancedServices.contains(service.getURL().getServiceUUID());
        boolean readOnly = !BluetoothUtils.hasWriteAccess(characteristic.getFlags());

        // making channel type that should match one of channel types from the "thing-types.xml" config file, these are:
        // characteristic-advanced-readonly-field
        // characteristic-advanced-editable-field
        // characteristic-readonly-field
        // characteristic-editable-field

        return String.format("characteristic%s%s-field",
                advanced ? "-advanced" : "", readOnly ? "-readonly" : "-editable");
    }

    private Map<String, String> getFieldProperties(Field field) {
        Map<String, String> properties = new HashMap<>();
        properties.put(BluetoothBindingConstants.PROPERTY_FIELD_NAME, field.getName());
        return properties;
    }

    private String getChannelLabel(String characteristicUUID, Field field) {
        Characteristic spec = handler.getGattParser().getCharacteristic(characteristicUUID);
        if (spec.getValue().getFields().size() > 1) {
            return spec.getName() + "/" + field.getName();
        } else {
            return spec.getName();
        }
    }

    private String getAcceptedItemType(Field field) {
        switch (field.getFormat().getType()) {
            case BOOLEAN: return "Switch";
            case UINT:
            case SINT:
            case FLOAT_IEE754:
            case FLOAT_IEE11073: return "Number";
            case UTF8S:
            case UTF16S: return "String";
            case STRUCT: return "Binary";
            default: throw new IllegalStateException("Unknown field format type");
        }
    }

}
