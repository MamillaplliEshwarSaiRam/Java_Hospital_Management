import java.io.*;
import java.util.*;

import Hospitalmanagement.Patient;
class MyException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String string1;
	MyException(String s)
	{
		string1=s;
	}
	@Override
	public String toString()
	{
		return("Exception occured: "+string1);
	}
}
class Hospital {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int capacity=10000;
	int ct;
	public int val() throws Exception
	{
		File f=new File("C:\\Users\\cse\\Desktop\\HospitalManager.txt");
		FileReader fr=new FileReader(f);
		BufferedReader s=new BufferedReader(new FileReader(f));
		ct=Integer.parseInt(s.readLine());
		System.out.println("Ct: "+ct);
		s.close();
	    fr.close();
		if(ct==capacity)
			return 0;
		ct+=1;
		FileWriter fout=new FileWriter("C:\\Users\\cse\\Desktop\\HospitalManager.txt"); 
		fout.write(Integer.toString(ct));;
		fout.close();
		return 1;
		
	}
}

class Patient_Util_Class extends Hospital implements Serializable,Patient 
{
	
	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	String name,gender,blood,phone;
	int age,weight,tiredness,chest_pain,freq_thirst;
	double bmi;
	double height;
	int freq_hunger,freq_urine,weight_loss,blur_vision,sweatness;
	int alcohol,exercise,junk,smoking;
	double severity_obesity,severity_type1,severity_type2,severity_heart;	
	Hospital h;
	transient Scanner sc=new Scanner(System.in);
	public Patient_Util_Class()
	{
			int ct=0;
			System.out.print("Enter name\n");
			name=sc.nextLine();
			System.out.print("Enter gender(M/F)\n");
			gender=sc.nextLine();
			System.out.print("Enter blood group\n");
			blood=sc.nextLine();
			System.out.print("Enter age\n");
			age=Integer.parseInt(sc.nextLine());
			System.out.print("Enter weight(in kgs)\n");
			weight=Integer.parseInt(sc.nextLine());
			System.out.print("Enter height(in metres)\n");
			height=Float.parseFloat(sc.nextLine());
			bmi=(weight)/(height*height);
			System.out.print("Enter phone number\n");
			phone=sc.nextLine();
			while(phone.length()!=10)
			{
				if(ct!=0)
					System.out.print("Invalid phone no.\nEnter again\n");
				phone=sc.nextLine();
				ct++;
			}
		}
		public Patient_Util_Class(char t)
		{
		}
		public void store()
		{
			try
			{
				FileOutputStream fout=new FileOutputStream("C:\\Users\\cse\\Desktop\\storage.txt",true);
				ObjectOutputStream os=new ObjectOutputStream(fout);
				os.writeObject(this);
				os.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public void symptom()
		{
			System.out.print("For the below symptos give range from 0 to 5\n");
			System.out.print("Frequency of hunger\n");
			freq_hunger=Integer.parseInt(sc.next());
			System.out.print("Frequency of thirst\n");
			freq_thirst=Integer.parseInt(sc.next());
			System.out.print("Frequency of urination\n");
			freq_urine=Integer.parseInt(sc.next());
			System.out.print("Blurred vision\n");
			blur_vision=Integer.parseInt(sc.next());
			System.out.print("Rapid weight loss without workout or dieting\n");
			weight_loss=Integer.parseInt(sc.next());
			System.out.print("Tiredness\n");
			tiredness=Integer.parseInt(sc.next());
			System.out.print("Chest pain\n");
			chest_pain=Integer.parseInt(sc.next());
			System.out.print("Sweatness\n");
			sweatness=Integer.parseInt(sc.next());
			System.out.print("Lack of proper exercise\n");
			exercise=Integer.parseInt(sc.next());
			System.out.print("Junk food\n");
			junk=Integer.parseInt(sc.next());
			System.out.print("Frequency of smoking\n");
			smoking=Integer.parseInt(sc.next());
			System.out.print("Last required detail\n");
			System.out.print("Alcohol intake\n");
			alcohol=Integer.parseInt(sc.next());
		}
		public void action_taker() throws Exception
		{
			if(severity_obesity>=3 ||severity_type1>=3||severity_type2>=3||severity_heart>=3)
			{
				System.out.println("You have to be hospitalised immediately");
				System.out.println("Please enter 1 for available our hospital services or 0");
				int c=sc.nextInt();
				if(c==1)
				{
					
					h=new Hospital();
					if(h.val()==1)
						System.out.println("Slots available");
					else
						throw new MyException("Slots not available");	
				}
			}
		}
				
		
		public void act() throws Exception
		{
			predictor();
			Thread.sleep(10000);
			tips();
			Thread.sleep(10000);
			medication();
			Thread.sleep(10000);
			store();
			action_taker();
		}
		public void predictor()
		{
			severity_obesity=obesity();
			severity_type1=type1_diabetes();
			severity_type2=type2_diabetes();
			severity_heart=heart_disease();
			System.out.println("Obesity severity: "+severity_obesity);
			System.out.println("Type 1 diabetes severity: "+severity_type1);
			System.out.println("Type 2 diabetes severity: "+severity_type2);
			System.out.println("Heart disease severity: "+severity_heart);
		}
		public double type1_diabetes()
		{
			int sev=0;
			if(tiredness>3)
				sev+=16;
			if(freq_urine>3)
				sev+=16;
			if(freq_thirst>3)
				sev+=16;
			if(freq_hunger>3)
				sev+=16;
			if(blur_vision>3)
				sev+=16;
			if(weight_loss>3)
				sev+=16;
			return sev;
		}
		public double type2_diabetes()
		{
			int sev=0;
			if(bmi<30)
				return 0;
			else
			{
				if(bmi>=30)
					sev+=14;
				if(tiredness>3)
					sev+=14;
				if(freq_urine>3)
					sev+=14;
				if(freq_thirst>3)
					sev+=14;
				if(freq_hunger>3)
					sev+=14;
				if(blur_vision>3)
					sev+=14;
				if(weight_loss>3)
					sev+=14;
				return sev;
			}
		}
		public double heart_disease()
		{
			int sev=0;
			if(chest_pain>3)
				sev+=33;
			if(sweatness>3)
				sev+=33;
			if(tiredness>3)
				sev+=33;

			return sev;
		}
		public double obesity()
		{
			 if(bmi<18)
			 {
				System.out.print("Underweight\n");
				return 100;
			 }
			 else if(bmi<25)
			 {
				System.out.print("Normal weight\n");
				return 0;
			 }
			 else if(bmi<30)
			 {
				System.out.print("Overweight\n");
				return  (50+(((bmi-25)/5)*10));
			 }
			 else
			 {
				System.out.print("Obese\n");
				return 100;
			 }
		}
		public void tips()
		{
			System.out.print("Health tips\n");
			if(exercise>0)
				System.out.print("Exercise for at least 1.5 hrs daily\n");
			if(junk>0)
				System.out.print("Avoid eating junk foods at all costs\n");
			if(smoking>0)
				System.out.print("Avoid smoking at all costs\n");
			if(severity_heart>0||severity_type1>0||severity_type2>0)
			{
				System.out.print("Meditate for .5 hr daily\n");
				System.out.print("Focus on the following food groups\n");
				System.out.print("1.Vegetables\n2.Whole grains\n3.Fruits\n");
				System.out.print("4.Fat_free dairy products\n");
				System.out.print("5.Lean protein sources such as chicken, fish and lean beef\n");
			}
			if(alcohol>0)
				System.out.print("Don't drink alcohol\n");
		}
		public void medication()
		{
			if(severity_type1>0)
			{
				System.out.print("Type 1 diabetes basic medication\n");
				System.out.print("Metformin\nPramlintide\n");
			}
			if(severity_type2>0)
			{
				System.out.print("Type 2 diabetes basic medication\n");
				System.out.print("Metformin\nSulfonylureas\n");
			}
			if(severity_heart>0)
			{
				System.out.print("Heart disease basic medication\n");
				System.out.print("Anti-clotting: Asprin\n");
				System.out.print("Antiplatent: Clopidogrel\n");
				System.out.print("Anticoagulant: Warfarin\n");
				System.out.print("Blood pressure: ACE inhibitors\n");
				System.out.print("Cholesterol: Statin\n");
				System.out.print("Anti anginal: Nitrate\n");
			}
			if(severity_obesity>0)
			{
				System.out.print("Obesity basic medication\n");
				System.out.print("Avoid junk food\n");
				System.out.print("Cut back on calories\n");
				System.out.print("Belviq\nContrave\n");
			}
		}
		public void show()
		{
			System.out.print("Name:"+name+"\n");
			System.out.print("Gender:"+gender +"\n");
			System.out.print("Age:"+age+"\n");
			System.out.print("Blood group:"+blood+"\n");
			System.out.print("Previous disease severities\n");
			System.out.print("Obesity: "+severity_obesity+"\n");
			System.out.print("Type 1 diabetes: "+severity_type1+"\n");
			System.out.print("Type 2 diabetes: "+severity_type2+"\n");
			System.out.print("Heart disease: "+severity_heart+"\n");
		}
		public int validate(String n,String p)
		{
			if(name.contentEquals(n) && phone.contentEquals(p))
				return 1;
			else
				return 0;
		}
		public void search(String n,String p)
		{
			int ct=0;
			try
			{
				FileInputStream fin=new FileInputStream("C:\\Users\\cse\\Desktop\\storage.txt");
				ObjectInputStream os=new ObjectInputStream(fin);
				if(fin.available()==0)
				{
					System.out.println("Nothing to search");
					return;
				}
				Patient_Util_Class obj=(Patient_Util_Class)os.readObject();
				while(obj!=null)
				{
					if(obj.validate(n,p)==1)
					{
						obj.show();
						ct++;
						break;
					}
					if(fin.available()!=0)
						obj=(Patient_Util_Class)os.readObject();
					else
						break;
				}
				os.close();
				if(ct==0)
					System.out.print("Not found\n");
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void delete_record(String n,String p) throws Exception
		{
			int ct=0;
			File f1=new File("C:\\Users\\cse\\Desktop\\storage.txt");
			FileInputStream fin=new FileInputStream(f1);
			ObjectInputStream os=new ObjectInputStream(fin);
			File f2=new File("tempfile.txt");
			FileOutputStream fout=new FileOutputStream("C:\\Users\\cse\\Desktop\\temp.txt");
			ObjectOutputStream is=new ObjectOutputStream(fout);
			if(fin.available()==0)
			{
				System.out.println("Nothing to delete");
				return;
			}
			Patient_Util_Class obj=(Patient_Util_Class)os.readObject();
			while(obj!=null)
			{
				if(obj.validate(n, p)==1)
				{
					System.out.print("Found and deleted\n");
					ct++;
				}
				else
					is.writeObject(obj);
				if(fin.available()!=0)
					obj=(Patient_Util_Class) os.readObject();
				else
					break;
			}
			os.close();
			is.close();
			f2.renameTo(f1);
			if(ct==0)
				System.out.print("Not found\n");	
		}
};
public class Util
{
	
	public static void main(String args[]) throws Exception
	{
		int ch=0;
		String name,phone;
		Scanner sc=new Scanner(System.in);
		Patient_Util_Class test=new Patient_Util_Class('t');
		while(ch!=4)
		{
			System.out.print("1.New patient\n2.Old patient\n3.Delete patient info\n");
			System.out.print("4.Exit\n");
			System.out.print("Enter your choice\n");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					Patient_Util_Class obj=new Patient_Util_Class();
					obj.symptom();
					obj.act();
					break;
				case 2:
					System.out.print("Enter name and phone no.\n");
					name=sc.next();
					phone=sc.next();
					test.search(name,phone);
					break;
				case 3:
					System.out.print("Enter name and phone no. for deletion\n");
					name=sc.next();
					phone=sc.next();
					test.delete_record(name,phone);
					break;
				case 4:
					System.out.print("Exiting\n");
					break;
				default:
					System.out.print("Invalid choice\n");
	
			}
		}
		
	}
}
