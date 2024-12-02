package enums;

public enum Size implements CharSequence {
    XXS,
    XS,
    XL,
    S,
    M,
    XXL,
    L;

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
