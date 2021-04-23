import java.util.Hashtable;

public class DNSService {
	private Hashtable<String, String> data = new Hashtable<>();

	public String register(String dnsName, String ipAddress){
		if(this.data.containsKey(dnsName)) {return "Already contains that DNS Name";}
		this.data.put(dnsName, ipAddress);
		return Integer.toString(this.data.size());
	}

	public String lookup(String dnsName){
		if(!this.data.containsKey(dnsName)) return "DNS Name not found";
		return this.data.get(dnsName);
	} 
}