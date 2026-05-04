package com.google.android.gms.internal;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zznfe' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public class zzegr {
    public static final zzegr zznew = new zzegr("DOUBLE", 0, zzegw.DOUBLE, 1);
    public static final zzegr zznex = new zzegr("FLOAT", 1, zzegw.FLOAT, 5);
    public static final zzegr zzney = new zzegr("INT64", 2, zzegw.LONG, 0);
    public static final zzegr zznez = new zzegr("UINT64", 3, zzegw.LONG, 0);
    public static final zzegr zznfa = new zzegr("INT32", 4, zzegw.INT, 0);
    public static final zzegr zznfb = new zzegr("FIXED64", 5, zzegw.LONG, 1);
    public static final zzegr zznfc = new zzegr("FIXED32", 6, zzegw.INT, 5);
    public static final zzegr zznfd = new zzegr("BOOL", 7, zzegw.BOOLEAN, 0);
    public static final zzegr zznfe;
    public static final zzegr zznff;
    public static final zzegr zznfg;
    public static final zzegr zznfh;
    public static final zzegr zznfi;
    public static final zzegr zznfj;
    public static final zzegr zznfk;
    public static final zzegr zznfl;
    public static final zzegr zznfm;
    public static final zzegr zznfn;
    private static final /* synthetic */ zzegr[] zznfq;
    private final zzegw zznfo;
    private final int zznfp;

    static {
        final int i = 3;
        final int i2 = 2;
        final String str = "STRING";
        final int i3 = 8;
        final zzegw zzegwVar = zzegw.STRING;
        zznfe = new zzegr(str, i3, zzegwVar, i2) { // from class: com.google.android.gms.internal.zzegs
            {
                int i4 = 8;
                int i5 = 2;
                zzegq zzegqVar = null;
            }
        };
        final String str2 = "GROUP";
        final int i4 = 9;
        final zzegw zzegwVar2 = zzegw.MESSAGE;
        zznff = new zzegr(str2, i4, zzegwVar2, i) { // from class: com.google.android.gms.internal.zzegt
            {
                int i5 = 9;
                int i6 = 3;
                zzegq zzegqVar = null;
            }
        };
        final String str3 = "MESSAGE";
        final int i5 = 10;
        final zzegw zzegwVar3 = zzegw.MESSAGE;
        zznfg = new zzegr(str3, i5, zzegwVar3, i2) { // from class: com.google.android.gms.internal.zzegu
            {
                int i6 = 10;
                int i7 = 2;
                zzegq zzegqVar = null;
            }
        };
        final String str4 = "BYTES";
        final int i6 = 11;
        final zzegw zzegwVar4 = zzegw.BYTE_STRING;
        zznfh = new zzegr(str4, i6, zzegwVar4, i2) { // from class: com.google.android.gms.internal.zzegv
            {
                int i7 = 11;
                int i8 = 2;
                zzegq zzegqVar = null;
            }
        };
        zznfi = new zzegr("UINT32", 12, zzegw.INT, 0);
        zznfj = new zzegr("ENUM", 13, zzegw.ENUM, 0);
        zznfk = new zzegr("SFIXED32", 14, zzegw.INT, 5);
        zznfl = new zzegr("SFIXED64", 15, zzegw.LONG, 1);
        zznfm = new zzegr("SINT32", 16, zzegw.INT, 0);
        zznfn = new zzegr("SINT64", 17, zzegw.LONG, 0);
        zznfq = new zzegr[]{zznew, zznex, zzney, zznez, zznfa, zznfb, zznfc, zznfd, zznfe, zznff, zznfg, zznfh, zznfi, zznfj, zznfk, zznfl, zznfm, zznfn};
    }

    private zzegr(String str, int i, zzegw zzegwVar, int i2) {
        this.zznfo = zzegwVar;
        this.zznfp = i2;
    }

    /* synthetic */ zzegr(String str, int i, zzegw zzegwVar, int i2, zzegq zzegqVar) {
        this(str, i, zzegwVar, i2);
    }

    public static zzegr[] values() {
        return (zzegr[]) zznfq.clone();
    }

    public final zzegw zzcdy() {
        return this.zznfo;
    }
}
