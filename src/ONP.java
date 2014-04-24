import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;  

public class ONP {

static Scanner sc = new Scanner(System.in);
public static String w;
public static double wynik;
public static String wP; 


public static int p(String op) 
{

if(op.equals("+") || op.equals("-")) 
	return 1;
if(op.equals("*") || op.equals("/")) 
	return 2;

return 0;
}

public static double d(char c, double x, double y)
{
	switch(c)
	{
		case '+':
			return y+x;
		case '-':
			return y-x;
		case '*':
			return y*x;
		case '/':
			return y/x;
	}
	return 0;
	
}

private static void zamiana() 
{
wP = "";  
wynik = 0;
boolean done;
Stack<String> stos = new Stack<String>();
StringTokenizer st = new StringTokenizer(w, "+-*/()", true);
     
while(st.hasMoreTokens()) 
{
	done = false;
	String s = st.nextToken();
	if(s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/")) 
	{
		while(!stos.empty() && p(stos.peek()) >= p(s)) 
			wP += stos.pop()  + " ";
		stos.push(s);
		done = true;
	}

	if(!done && s.equals("(")) 
	{
		stos.push(s);
		done = true;
	}

	if(!done && s.equals(")")) 
	{
		while(!stos.peek().equals("(")) 
			wP += stos.pop() + " ";
		stos.pop();
		done = true;
	}
	if(!done)
		wP += s  + " ";
}

while(!stos.empty()) 
	wP += stos.pop()  + " ";

}

public static double wynik()
{
	 Stack<Double> stosW = new Stack<Double>();
	 StringTokenizer st = new StringTokenizer(wP);
	 while(st.hasMoreTokens()) 
	 {
		 String s = st.nextToken();
		 if (s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/"))
		 {
			 double x = stosW.pop();
			 double y = stosW.pop();
			 stosW.push(d(s.charAt(0),x,y));
		 }
		 else
			 stosW.push(Double.parseDouble(s));


	}
	 return stosW.pop();
}


//metoda oblicza wartoœæ wyra¿enia postfiksowego
private static double oblicz() {

// tworzymy pusty stos
Stack<Double> stos = new Stack<Double>();
     
// dzielimy wyra¿enie postfiksowe na elementy na podstawie spacji
StringTokenizer st = new StringTokenizer(wP, " ");
     
// dopóki s¹ elementy w wyra¿eniu wejœciowym
while(st.hasMoreTokens()) {
// pobieramy element
String s = st.nextToken();

// jeœli element nie jest operatorem (czyli jest wartoœci¹)
if (!s.equals("+") && !s.equals("*") && !s.equals("-") && !s.equals("/")) {
// zamieniamy ³añcuch na liczbê
double wartosc = Double.parseDouble(s);
// odk³adamy wartoœæ na stos
stos.push(wartosc);
}
else {
//  jeœli element jest operatorem œci¹gamy dwie wartoœci ze stosu
double wartosc1 = stos.pop();
double wartosc2 = stos.pop();
// w zale¿noœci od operatora obliczamy wynik i odk³adamy go na stos
switch(s.charAt(0)) {
 case '*': {stos.push(wartosc2 * wartosc1); break;}
 case '+': {stos.push(wartosc2 + wartosc1); break;}
 case '-': {stos.push(wartosc2 - wartosc1); break;}
 case '/': {stos.push(wartosc2 / wartosc1); break;}
}
}
}
// zwracamy koñcowy wynik
return stos.pop();
}



public static void zKlaw()
{
	System.out.print ("Wyra¿enie infiksowe: \n> ");
	w = sc.next();
	zamiana();
	System.out.println("Wyra¿enie OPN: \n> "+wP+"= "+wynik());
	System.out.println();
}

public static void zPliku() throws FileNotFoundException
{
	w = "";
	File f = new File("D:\\workspace\\Lista3\\src\\dane.txt");
	Scanner r = new Scanner(f);
	while(r.hasNext())
	{
		w = r.nextLine();
		zamiana();
		System.out.println("Wyra¿enie OPN: \n> "+wP+"= "+wynik());
		System.out.println();
	}
	r.close();
	
}
	


public static void main(String args[]) throws FileNotFoundException 
{
	int pom = 0;
	do
	{
		System.out.println("MENU:");
		System.out.println("1 - wczytaj wyra¿enie z klawiatury");
		System.out.println("2 - wczytaj wyra¿enia z pliku tekstowego");
		System.out.println("3 - koniec");
		pom = sc.nextInt();
		if(pom == 1)
			zKlaw();
		if(pom == 2)
			zPliku();
	}
	while(pom!=3);


}

}

