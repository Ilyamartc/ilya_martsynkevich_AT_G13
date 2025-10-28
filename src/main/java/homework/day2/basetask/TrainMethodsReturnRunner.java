package lesson1.basetask;

public class TrainMethodsReturnRunner {
    public static void main(String[] args) {
        TrainMethodsReturn trainMethodsReturn = new TrainMethodsReturn();
        System.out.println(trainMethodsReturn.returnNewInt(12));
        System.out.println(trainMethodsReturn.returnNewLong(11111));
        System.out.println(trainMethodsReturn.returnNewChar('b'));
        System.out.println(trainMethodsReturn.returnNewFloat((float) 12.12));
        System.out.println(trainMethodsReturn.returnNewDouble(11111));
        System.out.println(trainMethodsReturn.returnNewShort((short)11111));
        System.out.println(trainMethodsReturn.returnNewByte((byte)11111));
        System.out.println(trainMethodsReturn.returnNewBoolean(true));
    }
}
