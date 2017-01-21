import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;	
import java.io.Writer;	
import java.lang.*;
import java.util.*;

class Contact{
	public int id;
	public String firstName,lastName,phone,email,company;

	public Contact(int id,String firstName,String lastName,String phone,String email,String company){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phone=phone;
		this.email=email;
		this.company=company;
	}
}


class Name{
	public String firstName;
	public String lastName;

	public Name(String name){	
		String[] fullName=Phonebook.getFullName(name).split(" ");
		this.firstName=fullName[0];
		this.lastName=fullName[1];
	}

	public Name(String firstName,String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
	}

	@Override
	public boolean equals(Object other){
		if(other==this) return true;
		if(other==null) return false;
		if(getClass()!=other.getClass()) return false;
		Name name=(Name) other;
		return (this.firstName.equals(name.firstName) && this.lastName.equals(name.lastName));
	}

	@Override
	public int hashCode(){
		String fullName=this.firstName+" "+this.lastName;
		return fullName.hashCode();
	}
}

class Phonebook{

	private static Map<Name,Contact> phoneBook;
	private static Map<String,String> names;

	public static void main(String[] args){
		phoneBook=new HashMap<Name,Contact>();
		names=new HashMap<String,String>();
		BufferedReader br=null;
		try{
			br=new BufferedReader(new FileReader("contacts.csv"));
			String line;
			line=br.readLine();
			while(line!=null){
				String[] tok=line.split(",");
				if(tok[0].equals("id")){
					line=br.readLine();
					continue;
				}
				Name name=new Name(tok[1],tok[2]);
				String fullName=tok[1]+" "+tok[2];
				names.put(tok[1],fullName);
				names.put(tok[2],fullName);
				Contact cont=new Contact(Integer.parseInt(tok[0]),tok[1],tok[2],tok[3],tok[4],tok[5]);
				phoneBook.put(name,cont);
				line=br.readLine();
			}

			BufferedReader brr=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw=new PrintWriter(System.out);

			String inp;
			inp=brr.readLine();
			while(inp!=null){
				Contact cont=phoneBook.get(new Name(inp));
				pw.print("First Name: ");
				pw.print(cont.firstName+"\r");
				pw.print("Last Name: ");
				pw.print(cont.lastName+"\r");
				pw.print("Phone: ");
				pw.print(cont.phone+"\r");
				pw.print("Email: ");
				pw.print(cont.email+"\r");
				pw.print("Company: ");
				pw.print(cont.company+"\r");
				inp=brr.readLine();
			}
			pw.flush();
			pw.close();
		}
		catch(Exception e){
			System.out.println("Error!");
		}
		
	}

	public static String getFullName(String name){
		return names.get(name);
	}

}