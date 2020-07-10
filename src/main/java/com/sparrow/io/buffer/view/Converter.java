package com.sparrow.io.buffer.view;

import com.sparrow.io.buffer.DataType;

public abstract class Converter<T> implements StringConverter<T> {

    public static final int DECIMAL = 0;
    public static final int HEX = 1;
    public static final int BINARY = 2;

    private final int type;

    public Converter(int type) {
        this.type = type;
    }

    public Converter() {
        this.type = 0;
    }

    public int getType() {
        return type;
    }

    public static class BooleanStringConverter extends Converter<Boolean> {

        @Override
        public String toString(Boolean object) {
            return object.toString();
        }

        @Override
        public Boolean fromString(String string) {
            return Boolean.valueOf(string);
        }

    }

    public static class ByteStringConverter extends Converter<Byte> {

        public ByteStringConverter() {
        }

        public ByteStringConverter(int type) {
            super(type);
        }

        @Override
        public String toString(Byte object) {
            switch (getType()) {
                case HEX:
                    return Integer.toHexString(Byte.toUnsignedInt(object));
                case BINARY:
                    return Integer.toBinaryString(Byte.toUnsignedInt(object));
                default:
                    return object.toString();
            }
        }

        @Override
        public Byte fromString(String string) {
            return Byte.valueOf(string);
        }

    }

    public static class ShortStringConverter extends Converter<Short> {

        public ShortStringConverter() {
        }

        public ShortStringConverter(int type) {
            super(type);
        }

        @Override
        public String toString(Short object) {
            switch (getType()) {
                case HEX:
                    return Integer.toHexString(Short.toUnsignedInt(object));
                case BINARY:
                    return Integer.toBinaryString(Short.toUnsignedInt(object));

                default:
                    return object.toString();
            }
        }

        @Override
        public Short fromString(String string) {
            return Short.valueOf(string);
        }

    }

    public static class IntegerStringConverter extends Converter<Integer> {

        public IntegerStringConverter() {
        }

        public IntegerStringConverter(int type) {
            super(type);
        }

        @Override
        public String toString(Integer object) {
            switch (getType()) {
                case HEX:
                    return Integer.toHexString(object);
                case BINARY:
                    return Integer.toBinaryString(object);

                default:
                    return object.toString();
            }
        }

        @Override
        public Integer fromString(String string) {
            return Integer.valueOf(string);
        }

    }

    public static class LongStringConverter extends Converter<Long> {

        public LongStringConverter() {
        }

        public LongStringConverter(int type) {
            super(type);
        }

        @Override
        public String toString(Long object) {
            switch (getType()) {
                case HEX:
                    return Long.toHexString(object);
                case BINARY:
                    return Long.toBinaryString(object);

                default:
                    return object.toString();
            }
        }

        @Override
        public Long fromString(String string) {
            return Long.valueOf(string);
        }

    }

    public static class FloatStringConverter extends Converter<Float> {

        public FloatStringConverter() {
        }

        public FloatStringConverter(int type) {
            super(type);
        }

        @Override
        public String toString(Float object) {
            switch (getType()) {
                case HEX:
                    return Integer.toHexString(Float.floatToIntBits(object));
                case BINARY:
                    return Integer.toBinaryString(Float.floatToIntBits(object));

                default:
                    return object.toString();
            }
        }

        @Override
        public Float fromString(String string) {
            return Float.valueOf(string);
        }

    }

    public static class DoubleStringConverter extends Converter<Double> {

        public DoubleStringConverter() {
        }

        public DoubleStringConverter(int type) {
            super(type);
        }

        @Override
        public String toString(Double object) {
            switch (getType()) {
                case HEX:
                    return Long.toHexString(Double.doubleToLongBits(object));
                case BINARY:
                    return Long.toBinaryString(Double.doubleToLongBits(object));
                default:
                    return object.toString();
            }
        }

        @Override
        public Double fromString(String string) {
            return Double.valueOf(string);
        }

    }

    public static Converter of(DataType dataType, int type) {

        switch (dataType) {

            case BOOLEAN:
                return new BooleanStringConverter();
            case BYTE:
                return new ByteStringConverter(type);
            case DOUBLE:
                return new DoubleStringConverter(type);
            case FLOAT:
                return new FloatStringConverter(type);
            case INTEGER:
                return new IntegerStringConverter(type);
            case LONG:
                return new LongStringConverter(type);
            case SHORT:
                return new ShortStringConverter(type);
            case USHORT:
                return new IntegerStringConverter(type);
        }
        throw new IllegalArgumentException("Unknown data type " + dataType);
    }

}
