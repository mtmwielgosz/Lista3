import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;  

public class ONP {

static Scanner sc = new Scanner(System.in);
public static boolean ZleZnaki;
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
	ZleZnaki = false;
	wP = "";  
	wynik = 0;
	boolean done;
	Stack<String> stos = new Stack<String>();
	StringTokenizer st = new StringTokenizer(w, "+-*/()",true);
     
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
		{
			try
			{
				Double.parseDouble(s);
				wP += s  + " ";
				done = true;
			}
			catch(NumberFormatException e)
			{
				ZleZnaki = true;
			}
		}

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


public static boolean ParzNaw() 
{
	int nawOt = 0;
	int nawZam = 0;
	String t;
	StringTokenizer st = new StringTokenizer(w, "()", true);
	while(st.hasMoreTokens()) 
	{
		t = st.nextToken();
		if(t.equals("("))
			nawOt++;
		if(t.equals(")"))
			nawZam++;
	}

return nawOt == nawZam;        
}




public static void zKlaw()
{
	System.out.print ("Wyra¿enie infiksowe: \n> ");
	w = sc.next();
	zamiana();
	if(ZleZnaki)
		System.out.println("Podano niedozwolone znaki (litery, znaki specjalne, inne).");
	else
		if(!ParzNaw())
			System.out.println("Nieparzysta liczba nawiasów.");
		else
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
		System.out.println("Wyra¿enie infiksowe: \n> "+w);
		zamiana();
		if(ZleZnaki)
			System.out.println("Podano niedozwolone znaki (litery, znaki specjalne, inne).");
		else
			if(!ParzNaw())
				System.out.println("Nieparzysta liczba nawiasów.");
			else
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
		
		try
		{
			if(pom == 1)
				zKlaw();
			if(pom == 2)
				zPliku();
		}
		catch (Exception e)
		{
			System.out.println("Znaleziono jeden lub wiêcej innych b³êdów w zapisie:\n- liczba ujemna w dzia³aniu (zamiast -x wpisz (0-x) np. (-2*3) -> ((0-2)*3),\n- wpisz * przed nawiasem, przez który chcesz pomno¿yæ,\n- inne b³êdy zapisu.");
			System.out.println();
		}
	}
	while(pom!=3);

}


}

