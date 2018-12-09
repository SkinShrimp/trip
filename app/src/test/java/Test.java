public class Test {

    public static void main(String args[]) {
        Integer i = null;
        switch (i) {
            case 1:
                System.out.println("男");
            case 0:
                System.out.println("女");
            default:
                System.out.println("保密");
        }
    }
}
