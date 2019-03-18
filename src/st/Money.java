package st;
public class Money {
    public int [] allmoney={50,20,5,5,1,1,1};
    public String givemoney(int need){
    	String finall="";
    	for(int i=0;i<7;i++){
    		if(i==6&&need>1){
    			finall="don't have enough money";
    		}else if(need>allmoney[i]){
    			finall+=allmoney[i]+",";
    			need=need-allmoney[i];
    		}else if(need==allmoney[i]){
    			finall+=allmoney[i];
    			need=0;
    		}
    	}
    	return finall;
    }
}

