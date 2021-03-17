/**
 * Automatically generated by Zserio Java extension version 2.2.0.
 * Generator setup: writerCode, pubsubCode, serviceCode, sqlCode.
 */

package example.calculator;

public class U64 implements zserio.runtime.io.InitializeOffsetsWriter, zserio.runtime.SizeOf
{
    public U64()
    {
    }

    public U64(java.io.File file)
            throws java.io.IOException, zserio.runtime.ZserioError
    {
        final zserio.runtime.io.FileBitStreamReader in = new zserio.runtime.io.FileBitStreamReader(file);
        read(in);
        in.close();
    }

    public U64(zserio.runtime.io.BitStreamReader in)
            throws java.io.IOException, zserio.runtime.ZserioError
    {
        read(in);
    }

    public U64(
        java.math.BigInteger value_)
    {
        setValue(value_);
    }

    @Override
    public int bitSizeOf()
    {
        return bitSizeOf(0);
    }

    @Override
    public int bitSizeOf(long bitPosition)
    {
        long endBitPosition = bitPosition;

        endBitPosition += 64;

        return (int)(endBitPosition - bitPosition);
    }

    public java.math.BigInteger getValue()
    {
        return this.value_;
    }

    public void setValue(java.math.BigInteger value_)
    {
        this.value_ = value_;
    }

    @Override
    public boolean equals(java.lang.Object obj)
    {
        if (obj instanceof U64)
        {
            final U64 that = (U64)obj;

            return
                    ((this.value_ == null) ? that.value_ == null : this.value_.equals(that.value_));
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        int result = zserio.runtime.Util.HASH_SEED;

        result = zserio.runtime.Util.HASH_PRIME_NUMBER * result +
                ((this.value_ == null) ? 0 : this.value_.hashCode());

        return result;
    }

    public void read(final zserio.runtime.io.BitStreamReader in)
            throws java.io.IOException, zserio.runtime.ZserioError
    {
        this.value_ = in.readBigInteger(64);
    }

    public long initializeOffsets(long bitPosition)
    {
        long endBitPosition = bitPosition;

        endBitPosition += 64;

        return endBitPosition;
    }

    public void write(java.io.File file) throws java.io.IOException, zserio.runtime.ZserioError
    {
        zserio.runtime.io.FileBitStreamWriter out = new zserio.runtime.io.FileBitStreamWriter(file);
        write(out);
        out.close();
    }

    @Override
    public void write(zserio.runtime.io.BitStreamWriter out)
            throws java.io.IOException, zserio.runtime.ZserioError
    {
        write(out, true);
    }

    @Override
    public void write(zserio.runtime.io.BitStreamWriter out, boolean callInitializeOffsets)
            throws java.io.IOException, zserio.runtime.ZserioError
    {
        out.writeBigInteger(getValue(), 64);
    }

    private java.math.BigInteger value_;
}
