//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: https://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.objects;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.stream.XMLStreamException;

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDLMSClient;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.GXDate;
import gurux.dlms.GXDateTime;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;

/**
 * Online help: <br>
 * https://www.gurux.fi/Gurux.DLMS.Objects.GXDLMSSpecialDaysTable
 */
public class GXDLMSSpecialDaysTable extends GXDLMSObject
        implements IGXDLMSBase {
    private GXDLMSSpecialDay[] entries;

    /**
     * Constructor.
     */
    public GXDLMSSpecialDaysTable() {
        this("0.0.11.0.0.255", 0);
    }

    /**
     * Constructor.
     * 
     * @param ln
     *            Logical Name of the object.
     */
    public GXDLMSSpecialDaysTable(final String ln) {
        this(ln, 0);
    }

    /**
     * Constructor.
     * 
     * @param ln
     *            Logical Name of the object.
     * @param sn
     *            Short Name of the object.
     */
    public GXDLMSSpecialDaysTable(final String ln, final int sn) {
        super(ObjectType.SPECIAL_DAYS_TABLE, ln, sn);
    }

    /**
     * @return Special day entries.
     */
    public final GXDLMSSpecialDay[] getEntries() {
        return entries;
    }

    /**
     * @param value
     *            Special day entries.
     */
    public final void setEntries(final GXDLMSSpecialDay[] value) {
        entries = value;
    }

    /**
     * Inserts a new entry in the table <br>
     * If a special day with the same index or with the same date as an already
     * defined day is inserted, the old entry will be overwritten.
     * 
     * @param client
     *            DLMS Client.
     * @param entry
     *            Inserted special day entry.
     * @return Generated byte array.
     * @throws NoSuchPaddingException
     *             No such padding exception.
     * @throws NoSuchAlgorithmException
     *             No such algorithm exception.
     * @throws InvalidAlgorithmParameterException
     *             Invalid algorithm parameter exception.
     * @throws InvalidKeyException
     *             Invalid key exception.
     * @throws BadPaddingException
     *             Bad padding exception.
     * @throws IllegalBlockSizeException
     *             Illegal block size exception.
     */
    public final byte[][] insert(final GXDLMSClient client,
            final GXDLMSSpecialDay entry)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        return insert(client, new GXDLMSSpecialDay[] { entry });
    }

    /**
     * Inserts a new entry in the table <br>
     * If a special day with the same index or with the same date as an already
     * defined day is inserted, the old entry will be overwritten.
     * 
     * @param client
     *            DLMS Client.
     * @param entries
     *            Inserted special day entries.
     * @return Generated byte array.
     * @throws NoSuchPaddingException
     *             No such padding exception.
     * @throws NoSuchAlgorithmException
     *             No such algorithm exception.
     * @throws InvalidAlgorithmParameterException
     *             Invalid algorithm parameter exception.
     * @throws InvalidKeyException
     *             Invalid key exception.
     * @throws BadPaddingException
     *             Bad padding exception.
     * @throws IllegalBlockSizeException
     *             Illegal block size exception.
     */
    public final byte[][] insert(final GXDLMSClient client,
            final GXDLMSSpecialDay[] entries)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        GXByteBuffer bb = new GXByteBuffer();
        bb.setUInt8(DataType.ARRAY.getValue());
        GXCommon.setObjectCount(entries.length, bb);
        for (GXDLMSSpecialDay entry : entries) {
            bb.setUInt8(DataType.STRUCTURE.getValue());
            bb.setUInt8(3);
            GXCommon.setData(null, bb, DataType.UINT16, entry.getIndex());
            GXCommon.setData(null, bb, DataType.OCTET_STRING, entry.getDate());
            GXCommon.setData(null, bb, DataType.UINT8, entry.getDayId());
        }
        return client.method(this, 1, bb.array(), DataType.ARRAY);
    }

    /**
     * Deletes an entry in the table.
     * 
     * @param client
     *            DLMS Client.
     * @param entry
     *            Deleted special day item.
     * @return Generated byte array.
     * @throws NoSuchPaddingException
     *             No such padding exception.
     * @throws NoSuchAlgorithmException
     *             No such algorithm exception.
     * @throws InvalidAlgorithmParameterException
     *             Invalid algorithm parameter exception.
     * @throws InvalidKeyException
     *             Invalid key exception.
     * @throws BadPaddingException
     *             Bad padding exception.
     * @throws IllegalBlockSizeException
     *             Illegal block size exception.
     */
    public final byte[][] delete(final GXDLMSClient client,
            final GXDLMSSpecialDay entry)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        return client.method(this, 2, entry.getIndex(), DataType.UINT16);
    }

    @Override
    public final Object[] getValues() {
        return new Object[] { getLogicalName(), getEntries() };
    }

    @Override
    public final int[] getAttributeIndexToRead(final boolean all) {
        java.util.ArrayList<Integer> attributes =
                new java.util.ArrayList<Integer>();
        // LN is static and read only once.
        if (all || getLogicalName() == null
                || getLogicalName().compareTo("") == 0) {
            attributes.add(1);
        }
        // Entries
        if (all || !isRead(2)) {
            attributes.add(2);
        }
        return GXDLMSObjectHelpers.toIntArray(attributes);
    }

    /*
     * Returns amount of attributes.
     */
    @Override
    public final int getAttributeCount() {
        return 2;
    }

    /*
     * Returns amount of methods.
     */
    @Override
    public final int getMethodCount() {
        return 2;
    }

    @Override
    public final DataType getDataType(final int index) {
        if (index == 1) {
            return DataType.OCTET_STRING;
        }
        if (index == 2) {
            return DataType.ARRAY;
        }
        throw new IllegalArgumentException(
                "getDataType failed. Invalid attribute index.");
    }

    /*
     * Returns value of given attribute.
     */
    @Override
    public final Object getValue(final GXDLMSSettings settings,
            final ValueEventArgs e) {
        if (e.getIndex() == 1) {
            return GXCommon.logicalNameToBytes(getLogicalName());
        }
        if (e.getIndex() == 2) {
            GXByteBuffer data = new GXByteBuffer();
            data.setUInt8(DataType.ARRAY.getValue());
            if (entries == null) {
                GXCommon.setObjectCount(0, data);
            } else {
                int cnt = entries.length;
                // Add count
                GXCommon.setObjectCount(cnt, data);
                for (GXDLMSSpecialDay it : entries) {
                    data.setUInt8(DataType.STRUCTURE.getValue());
                    data.setUInt8(3); // Count
                    GXCommon.setData(null, data, DataType.UINT16,
                            it.getIndex());
                    GXCommon.setData(null, data, DataType.OCTET_STRING,
                            it.getDate());
                    GXCommon.setData(null, data, DataType.UINT8, it.getDayId());
                }
            }
            return data.array();
        }
        e.setError(ErrorCode.READ_WRITE_DENIED);
        return null;
    }

    /*
     * Set value of given attribute.
     */
    @Override
    public final void setValue(final GXDLMSSettings settings,
            final ValueEventArgs e) {
        if (e.getIndex() == 1) {
            setLogicalName(GXCommon.toLogicalName(e.getValue()));
        } else if (e.getIndex() == 2) {
            entries = null;
            if (e.getValue() != null) {
                java.util.ArrayList<GXDLMSSpecialDay> items =
                        new java.util.ArrayList<GXDLMSSpecialDay>();
                for (Object i : (List<?>) e.getValue()) {
                    List<?> item = (List<?>) i;
                    GXDLMSSpecialDay it = new GXDLMSSpecialDay();
                    it.setIndex(((Number) item.get(0)).intValue());
                    it.setDate((GXDateTime) GXDLMSClient
                            .changeType((byte[]) item.get(1), DataType.DATE));
                    it.setDayId(((Number) item.get(2)).intValue());
                    items.add(it);
                }
                entries = items.toArray(new GXDLMSSpecialDay[0]);
            }
        } else {
            e.setError(ErrorCode.READ_WRITE_DENIED);
        }
    }

    @Override
    public final byte[] invoke(final GXDLMSSettings settings,
            final ValueEventArgs e) {
        if (e.getIndex() != 1 && e.getIndex() != 2) {
            e.setError(ErrorCode.READ_WRITE_DENIED);
        } else {
            List<GXDLMSSpecialDay> items = new ArrayList<GXDLMSSpecialDay>();
            if (entries != null) {
                items.addAll(Arrays.asList(entries));
            }
            if (e.getIndex() == 1) {
                List<?> tmp = (List<?>) e.getParameters();
                GXDLMSSpecialDay it = new GXDLMSSpecialDay();
                it.setIndex(((Number) tmp.get(0)).intValue());
                it.setDate((GXDate) GXDLMSClient.changeType((byte[]) tmp.get(1),
                        DataType.DATE));
                it.setDayId(((Number) tmp.get(2)).intValue());
                for (GXDLMSSpecialDay item2 : items) {
                    if (item2.getIndex() == it.getIndex()) {
                        items.remove(item2);
                        break;
                    }
                }
                items.add(it);
            } else if (e.getIndex() == 2) {
                int index = ((Number) e.getParameters()).intValue();
                for (GXDLMSSpecialDay item : items) {
                    if (item.getIndex() == index) {
                        items.remove(item);
                        break;
                    }
                }
            }
            entries = items.toArray(new GXDLMSSpecialDay[0]);
        }
        return null;
    }

    @Override
    public final void load(final GXXmlReader reader) throws XMLStreamException {
        List<GXDLMSSpecialDay> list = new ArrayList<GXDLMSSpecialDay>();
        if (reader.isStartElement("Entries", true)) {
            while (reader.isStartElement("Entry", true)) {
                GXDLMSSpecialDay it = new GXDLMSSpecialDay();
                it.setIndex(reader.readElementContentAsInt("Index"));
                it.setDate(reader.readElementContentAsDate("Date"));
                it.setDayId(reader.readElementContentAsInt("DayId"));
                list.add(it);
            }
            reader.readEndElement("Entries");
        }
        entries = list.toArray(new GXDLMSSpecialDay[list.size()]);
    }

    @Override
    public final void save(final GXXmlWriter writer) throws XMLStreamException {
        if (entries != null) {
            writer.writeStartElement("Entries");
            for (GXDLMSSpecialDay it : entries) {
                writer.writeStartElement("Entry");
                writer.writeElementString("Index", it.getIndex());
                writer.writeElementString("Date", it.getDate());
                writer.writeElementString("DayId", it.getDayId());
                writer.writeEndElement();
            }
            writer.writeEndElement();
        }
    }

    @Override
    public final void postLoad(final GXXmlReader reader) {
        // Not needed for this object.
    }
}