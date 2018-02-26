public class Tester {
    public static void English(Object o){
        System.out.println("Other");
    }

    public static void English(String s){
        System.out.println("Thing");
    }
    public static void English(Integer o){
        System.out.println("int");
    }


    public static void main(String[] args){
        //char v10 = 'a';
        //final char v11 = v10;
        //String v12 = v11 + "eiou";
        //final String v13 = v12;
        //char[] v14 = new char[] {v10, 'e', 'i', 'o', 'u'};
        //final char[] v15 = v14;

        //v10 = 'y';
        //v11 = v10; - no
        //v12 = "uoie" + v11;
        //v13 = v12; - no
        //v12[0] = 'x'; - no

        //v13[0] = 0; - no
        //v14 = v15;
        //v15 = v14; - no
        //v14[0] = 'x';
        //v15[0] = 'z';

        //Integer a = 1000, b = 1000;
        //System.out.println(a == b);

        //Integer c = 100, d = 100;
        //System.out.println(c == d);
        //short s = 0;
        //int z = 123456;
        //int x = 07;
        //int y = 08;
        //s+=z;

        //System.out.println("" + s);

        //String original = "software";
        //StringBuilder result = new StringBuilder("hi");
        //int index = original.indexOf('a');
        //result.setCharAt(0, original.charAt(0));
        //result.setCharAt(1, original.charAt(original.length() - 1));
        //result.insert(1, original.charAt(4));
        //result.append(original.substring(1,4));
        //result.insert(3, (original.substring(index, index+2) + " "));
        //System.out.println(result);

        //Computer a = new Computer();
        //Computer b = new Computer();
//
        //a.y = 5;
        //b.y = 6;
        //a.x = 1;
        //a.x = 2;
//
        //System.out.println(a.y);
        //System.out.println(b.y);
        //System.out.println(a.x);
        //System.out.println(b.x);
        //System.out.println(Computer.x);

        int x = 10;
        Ex2 e = new Ex2();
        System.out.println(e.x);

    }
}
