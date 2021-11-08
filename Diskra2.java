import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Diskra2 {

    public static void prnt(ArrayList<Integer> s){
        System.out.print(s.get(0));
        System.out.print('.');
        for(int i = 1; i < s.size(); i++){
            System.out.print(s.get(i));
        }
        System.out.println("");
    }

    public static ArrayList<Integer> retPK(int a){
        a = (a + 128) % 256 - 128;
        if(a == -128 || a == 128) {
            ArrayList<Integer> s = new ArrayList<>();
            s.add(1);
            for(int i = 1; i < 8; i++)
                s.add(1);
            return s;
        }
        int b = a;
        a = Math.abs(a);
        ArrayList<Integer> s = new ArrayList<>();

        while(a > 0) {
            s.add(a % 2);
            a /= 2;
        }
        while(s.size() < 7){
            s.add(0);
        }
        if (b <= 0)
            s.add(1);
        else
            s.add(0);

        Collections.reverse(s);
        return s;
    }

    public static ArrayList<Integer> ret(int a){
        a = (a + 128 + 256) % 256 - 128;
        if(a == -128 || a == 128) {
            ArrayList<Integer> s = new ArrayList<>();
            s.add(1);
            for(int i = 1; i < 8; i++)
                s.add(0);
            return s;
        }
        int b = a;
        a = Math.abs(a);
        ArrayList<Integer> s = new ArrayList<>();

        while(a > 0) {
            s.add(a % 2);
            a /= 2;
        }
        while(s.size() < 7){
            s.add(0);
        }
        if(b <= 0) {
            int j = s.size();
            for (int i = 0; i < s.size(); i++) {
                if (s.get(i) != 0){
                    j = i + 1;
                    i = s.size();
                }
            }
            for(int i = j; i < s.size(); i++) {
                if (s.get(i) == 0)
                    s.set(i, 1);
                else
                    s.set(i, 0);
            }
            s.add(1);
        }
        else
            s.add(0);
        Collections.reverse(s);
        return s;
    }

    public static void fullprint(int a, char c, boolean WasPK){
        System.out.print(c);
        System.out.print("(");
        if(WasPK || (a >= 0 && a < 128)){
            System.out.print("ПК) = ");
            prnt(retPK(a));
        }
        else{
            System.out.print("ДК) = ");
            prnt(ret(a));
        }
    }

    public static int bzi(int a){
        if(a < 0)
            a += 256;
        return a;
    }

    public static void printflag(int CF, int SF, int ZF, int AF, int PF, int OF){
        System.out.print("CF = ");
        System.out.print(CF);
        System.out.print("; SF = ");
        System.out.print(SF);
        System.out.print("; ZF = ");
        System.out.print(ZF);
        System.out.print("; AF = ");
        System.out.print(AF);
        System.out.print("; PF = ");
        System.out.print(PF);
        System.out.print("; OF = ");
        System.out.println(OF);
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Решение Д/З по дискретной математике №2");
        System.out.println("Переносы нужно будет прописывать вручную (это очень легко, я в вас верю)");
        System.out.println("Везде, где в БзИ есть ? нужно пояснить причину возникновения ошибки. Зачастую - переполнение");
        System.out.println("Знак вопрос в начале строки - знак следовательно из предыдущей строки");
        System.out.println("Остальное за вас сделает программа");
        System.out.println("Введите Числа А и В через пробел");
        int a0 = sc.nextInt();
        int b0 = sc.nextInt();
        for(int i = 0; i < 4; i++){
            int OF = 0, CF = 0, SF = 0, ZF = 0, PF = 0, AF = 0;
            int a = (int) (a0 * Math.pow(-1, i/2));
            int b = (int) (b0 * Math.pow(-1, i%2));
            System.out.print("№1.");
            System.out.print(i+1);
            System.out.print(" : A = ");
            System.out.print(a);
            System.out.print("; B = ");
            System.out.println(b);
            int c = (a + b + 128 + 256) % 256 - 128;
            fullprint(a, 'A', false);
            fullprint(b, 'B', false);
            System.out.println("        _________");
            fullprint(c, 'C', false);
            if(ret(a + b).get(0) == 1) {
                System.out.print("⤷");
                fullprint(a + b, 'C', true);
                if(c == 128 || c == -128){
                    System.out.println("Для +-128 не существует ПК, так что писать только ДК, а ЗИ и БзИ некорректны из-за переполнения");
                }
            }
            System.out.println("==================");
            System.out.println("Знаковый интерпретатор:");
            System.out.println(a);
            System.out.println(b);
            System.out.println("____");
            System.out.print(c);
            if(c < a + b)
                System.out.println("?");
            else
                System.out.println("");
            System.out.println("==================");
            System.out.println("Беззнаковый интерпретатор:");
            System.out.println(bzi(a));
            System.out.println(bzi(b));
            System.out.println("____");
            System.out.print(bzi(c));
            if(bzi(c)!=c && - 256 + bzi(c) != c)
                System.out.println("?");
            else
                System.out.println("");
            if(bzi(c) < bzi(a) + bzi(b))
                CF = 1;
            SF = ret(a + b).get(0);
            if(c  == 0)
                ZF = 1;
            if(ret(a).get(7)+ret(a).get(6)*2+ret(a).get(5)*4+ret(a).get(4)*8+ret(b).get(7)+ret(b).get(6)*2+ret(b).get(5)*4+ret(b).get(4)*8 > 15)
                AF = 1;
            if((ret(c).get(7)+ret(c).get(6)+ret(c).get(5)+ret(c).get(4)+ret(c).get(3)+ret(c).get(2)+ret(c).get(1)+ret(c).get(0)) % 2 == 0)
                PF = 1;
            if((ret(a).get(0) == ret(a).get(0)) && (ret(a).get(0) != ret(a + b).get(0)))
                OF = 1;
            printflag(CF, SF, ZF, AF, PF, OF);
            System.out.println("==================");
            System.out.println("##################");
            System.out.println("==================");
        }
        System.out.println("Выше находится полное решение первого пункта. Далее вы увидите возможные варианты для числа B во 2 пункте. Введите любое число как будете готовы");
        int exexexe = sc.nextInt();
        int a = a0;
        int save_b = 0, save_b1 = 0;

        System.out.println("Из следующего списка выберите 2 числа");
        for(int f = -127; f < 128; f++) {
            if((ret(a0).get(0) == ret(f).get(0)) && (ret(a0).get(0) != ret(a0 + f).get(0))){
                System.out.print(f);
                System.out.print(", ");
            }
        }
        System.out.println("");
        System.out.println("Введите заинтересовавшие вас числа (если они будут некорректными, то капзда проге)");
        int flg = sc.nextInt();
        int flg1 = sc.nextInt();
        save_b = flg;
        save_b1 = flg1;
        System.out.print("Ваши числа: ");
        System.out.print(save_b);
        System.out.print(", ");
        System.out.println(save_b1);
        for(int i = 0; i < 2; i++) {
            int b = 0;
            if(i == 0) {
                b = save_b;
            }
            else{
                b = save_b1;
            }
            a = a0;
            System.out.print("№2.");
            System.out.print(i + 1);
            System.out.print(" : A = ");
            System.out.print(a);
            System.out.print("; B = ");
            System.out.println(b);
            int OF = 0, CF = 0, SF = 0, ZF = 0, PF = 0, AF = 0;
            int c = (a + b + 128 + 256) % 256 - 128;
            fullprint(a, 'A', false);
            fullprint(b, 'B', false);
            System.out.println("        _________");
            fullprint(c, 'C', false);
            if (ret(a + b).get(0) == 1) {
                System.out.print("⤷");
                fullprint(a + b, 'C', true);
                if (c == 128 || c == -128) {
                    System.out.println("Для +-128 не существует ПК, так что писать только ДК, а ЗИ и БзИ некорректны из-за переполнения");
                }
            }
            System.out.println("==================");
            System.out.println("Знаковый интерпретатор:");
            System.out.println(a);
            System.out.println(b);
            System.out.println("____");
            System.out.print(c);
            if (c < a + b)
                System.out.println("?");
            else
                System.out.println("");
            System.out.println("==================");
            System.out.println("Беззнаковый интерпретатор:");
            System.out.println(bzi(a));
            System.out.println(bzi(b));
            System.out.println("____");
            System.out.print(bzi(c));
            if (bzi(c)!=c && - 256 + bzi(c) != c)
                System.out.println("?");
            else
                System.out.println("");
            if (bzi(c) < bzi(a) + bzi(b))
                CF = 1;
            SF = ret(a + b).get(0);
            if (c == 0)
                ZF = 1;
            if (ret(a).get(7) + ret(a).get(6) * 2 + ret(a).get(5) * 4 + ret(a).get(4) * 8 + ret(b).get(7) + ret(b).get(6) * 2 + ret(b).get(5) * 4 + ret(b).get(4) * 8 > 15)
                AF = 1;
            if ((ret(c).get(7) + ret(c).get(6) + ret(c).get(5) + ret(c).get(4) + ret(c).get(3) + ret(c).get(2) + ret(c).get(1) + ret(c).get(0)) % 2 == 0)
                PF = 1;
            if ((ret(a).get(0) == ret(a).get(0)) && (ret(a).get(0) != ret(a + b).get(0)))
                OF = 1;
            printflag(CF, SF, ZF, AF, PF, OF);
            System.out.println("==================");
            System.out.println("##################");
            System.out.println("==================");
        }
        System.out.print("Выше находится полное решение второго пункта. Для пункта 3 ваше число - ");
        System.out.println(128-b0);
        for(int i = 0; i < 2; i++) {
            int b = 0;
            b = (int) ((128 - b0) * Math.pow(-1, i));
            a = (int) (b0 * Math.pow(-1, i));
            System.out.print("№3.");
            System.out.print(i + 1);
            System.out.print(" : A = ");
            System.out.print(a);
            System.out.print("; B = ");
            System.out.println(b);
            int OF = 0, CF = 0, SF = 0, ZF = 0, PF = 0, AF = 0;
            int c = (a + b + 128 + 256) % 256 - 128;
            fullprint(a, 'A', false);
            fullprint(b, 'B', false);
            System.out.println("        _________");
            fullprint(c, 'C', false);
            if (ret(a + b).get(0) == 1) {
                System.out.print("⤷");
                fullprint(a + b, 'C', true);
                if (c == 128 || c == -128) {
                    System.out.println("Для +-128 не существует ПК, так что писать только ДК, а ЗИ и БзИ некорректны из-за переполнения");
                }
            }
            System.out.println("==================");
            System.out.println("Знаковый интерпретатор:");
            System.out.println(a);
            System.out.println(b);
            System.out.println("____");
            System.out.print(c);
            if (c < a + b)
                System.out.println("?");
            else
                System.out.println("");
            System.out.println("==================");
            System.out.println("Беззнаковый интерпретатор:");
            System.out.println(bzi(a));
            System.out.println(bzi(b));
            System.out.println("____");
            System.out.print(bzi(c));
            if (bzi(c) !=c && - 256 + bzi(c) != c)
                System.out.println("?");
            else
                System.out.println("");
            if (bzi(c) < bzi(a) + bzi(b))
                CF = 1;
            SF = ret(a + b).get(0);
            if (c == 0)
                ZF = 1;
            if (ret(a).get(7) + ret(a).get(6) * 2 + ret(a).get(5) * 4 + ret(a).get(4) * 8 + ret(b).get(7) + ret(b).get(6) * 2 + ret(b).get(5) * 4 + ret(b).get(4) * 8 > 15)
                AF = 1;
            if ((ret(c).get(7) + ret(c).get(6) + ret(c).get(5) + ret(c).get(4) + ret(c).get(3) + ret(c).get(2) + ret(c).get(1) + ret(c).get(0)) % 2 == 0)
                PF = 1;
            if ((ret(a).get(0) == ret(a).get(0)) && (ret(a).get(0) != ret(a + b).get(0)))
                OF = 1;
            printflag(CF, SF, ZF, AF, PF, OF);
            System.out.println("==================");
            System.out.println("##################");
            System.out.println("==================");
        }
    }

}