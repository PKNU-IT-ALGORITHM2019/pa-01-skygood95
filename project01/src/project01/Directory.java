package project01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Directory {
	static int size=0;
	static Dir []dec=new Dir[200000];
	static int fins=0;
	static int number=0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner kb= new Scanner (System.in);
		
		while(true) {
			System.out.print("$ ");
			String a=kb.next();
			String b=null;
			switch(a) {	
			case"read":
				b=kb.next();
				read(b);
				break;
			case"size":
				System.out.println(size);
				break;
			case"find":
				b=kb.nextLine();
				b=b.substring(1);
				fins=0;
				number=0;
				int q=find(b,0,size-1);
				if(fins==-1) {
					System.out.println("Not Found.");
					if(q!=-1)	
						System.out.println(dec[q].word+" "+dec[q].num);
					System.out.println("---");
					if(q!=size-1)
						System.out.println(dec[q+1].word+" "+dec[q+1].num);
				}
				else
				{
					int npred=pre(q);
					int nnext=next(q);
					number=npred+nnext+1;
					System.out.println("Founds "+number+" items.");
					for(int i=q-npred;i<=q+nnext;i++)
					{
						System.out.println(dec[i].word+" "+dec[i].num+" "+dec[i].dec);
					}
				}
				break;
			case"exit":
				System.exit(0);
				kb.close();
				break;
			}
		}
	}
	public static void read(String file) {
		String a= null;
		String b= null;
		String c= null;
		String d= null;
		try {
			Scanner s=new Scanner(new File(file));
			
			while(s.hasNext()) {
				a=s.nextLine();	
				if(a.equals("")) {
					continue;
				}
				b=a.split("\\(")[0];
				b=a.substring(0,b.length()-1);
				c=a.split("\\)")[0];
				c=a.substring(b.length(),c.length());
				d=a.substring(b.length()+c.length());
				dec[size]=new Dir(b,c,d);
				size++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int find(String a,int begin,int finish) {
		if(begin>finish) {
			fins=-1;
			if(finish==-1) { //
				return -1;
			}
			return finish; //결국 피니쉬가 앞에것으로 됨
		}
		int mid=(begin+finish)/2;
		if(dec[mid].word.compareToIgnoreCase(a)==0) {

			return mid;
		}
		else if(dec[mid].word.compareToIgnoreCase(a)>0) {
			return find(a,begin,mid-1);
		}
		else {
			return find(a,mid+1,finish);
		}
		
	}
	public static int pre(int min) {
		int i=min-1;
		int numv=0;
		while(dec[i].word.compareToIgnoreCase(dec[min].word)==0)
		{
			numv++;
			i--;
		}
		return numv;
	}
	public static int next(int min) {
		int i=min+1;
		int numv=0;
		while(dec[i].word.compareToIgnoreCase(dec[min].word)==0)
		{
			numv++;
			i++;
		}
		return numv;
	}
}
