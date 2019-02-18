/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_2;

import java.util.Random;

/**
 *
 * @author Amos
 */
public class HugeInteger {
    private int size;
    private String hugeInt;
    private boolean sign;
    
    
    public int getSize(){
       return size;
   }
    public boolean getSign(){
       return sign;
   }
    
    public HugeInteger(int n)throws IndexOutOfBoundsException{
        if (n<0)
            throw new IndexOutOfBoundsException("Invalid index");
        size = n;
        Random num = new Random();

        StringBuilder numGen = new StringBuilder("");
        numGen.append(num.nextInt(9) + 1);
        for(int i = 0; i < n - 1; i++){
            numGen.append(num.nextInt(10));
        }
        
        sign = num.nextBoolean();

        hugeInt = numGen.toString();
    }
    
    public HugeInteger(String val) throws IllegalArgumentException{
        size = val.length();
        sign = true;
        int change = 0;
        if (val.charAt(0) == '-'){
            sign = false;
            change++;
            size--;
            if (size==0){
                throw new IllegalArgumentException ("Input is not a number");
            }
        } 
        for (int j=change; j<size+change-1;j++){
            if(val.charAt(change)=='0'){
                size--;
                change++;
                if (size==0){
                    hugeInt ="0";
                    sign = true;
                    size=1;
                    break;
                }
            }
        }

        if(size>=1){
            for (int i=change; i<size+change; i++){
                if((int)val.charAt(i)<48||(int)val.charAt(i)>57)
                    throw new IllegalArgumentException ("Input is not a number");
            
                hugeInt=val.substring(change, val.length()); 
            }
        }
    }
    
    public String toString(){
        return (this.sign ? hugeInt : "-" + hugeInt);
    }
    
    public HugeInteger add(HugeInteger h){
        String outAdd = "";
        String Big = h.hugeInt;
        String Small =hugeInt;
        int BigSize = h.size;
        int SmallSize = this.size;

        int carry=0;
        
        if(this.absolute().compareTo(h.absolute())==1){
            Big = hugeInt;
            Small= h.hugeInt;
            BigSize = this.size;
            SmallSize = h.size;
        }
        
        if(this.sign&&h.sign==false){
            return this.subtract(h.absolute());
        }
        
        else if(this.sign==false&&h.sign){
            return h.subtract(this.absolute());
        }
        
        else if(this.sign==h.sign){
            Big = new StringBuilder(Big).reverse().toString();
            Small = new StringBuilder(Small).reverse().toString();
        
        
            for (int i=0; i<SmallSize; i++){
                int sum1 = ((int)(Small.charAt(i)-'0') + (int)(Big.charAt(i)-'0') +carry);
                outAdd += (char) (sum1%10+'0');
                carry = sum1/10;
            }
            
            for (int i=SmallSize; i<BigSize; i++)
            {
                int sum2 = ((int)(Big.charAt(i)-'0')+carry);
                outAdd += (char)(sum2%10+'0');
                carry = sum2/10;
            }

            if(carry>0)
                outAdd+= (char)(carry+ '0');
            outAdd = new StringBuilder(outAdd).reverse().toString();
            


            if(this.sign==false&&h.sign==false){
                outAdd = "-" + outAdd;
            }
        }
        
        HugeInteger add = new HugeInteger(outAdd);
        
        return add;
    }
    
    public HugeInteger subtract(HugeInteger h){
        String outSub = "";
        String Big = hugeInt;
        String Small =h.hugeInt;
        int BigSize = this.size;
        int SmallSize = h.size;

        int borrow=0;

        if(this.absolute().compareTo(h.absolute())==-1){
            Big = h.hugeInt;
            Small= hugeInt;
            BigSize = h.size;
            SmallSize = this.size;
        }
        
        if(this.sign&&h.sign==false){
            return this.add(h.absolute());
        }
        
        else if(this.sign==false&&h.sign){
            HugeInteger neg = h.add(this.absolute());
            neg.sign=false;
            return neg;
        }

        else if(this.sign==h.sign){
            Big = new StringBuilder(Big).reverse().toString();
            Small = new StringBuilder(Small).reverse().toString();


            for (int i=0; i<SmallSize; i++){
                int sub1 = ((int)(Big.charAt(i)-'0') - (int)(Small.charAt(i)-'0') -borrow);
                if (sub1<0)
                {
                    sub1+=10;
                    borrow =1;
                }

                else{
                    borrow =0;
                }
                outSub += (char) (sub1+'0');

            }

            for (int i=SmallSize; i<BigSize; i++)
            {
                int sub2 = ((int)(Big.charAt(i)-'0')-borrow);
                if (sub2<0)
                {
                    sub2+=10;
                    borrow =1;
                }

                else{
                    borrow =0;
                }
                outSub += (char) (sub2+'0');
            }
            outSub = new StringBuilder(outSub).reverse().toString();
        }

        HugeInteger sub = new HugeInteger(outSub);
        if (this.compareTo(h)==-1){
            sub.sign=false;
        }
        return sub;
    }
    
    public HugeInteger multiply(HugeInteger h){
        String mul="";
        String s1 = new StringBuffer(hugeInt).reverse().toString();
        String s2 = new StringBuffer(h.hugeInt).reverse().toString();
        int store[] = new int[this.size+h.size];
        
        for (int i=0; i<this.size; i++){
            for (int k=0; k<h.size; k++){
                store[i+k]+= (s1.charAt(i)-'0')*(s2.charAt(k)-'0');
            }
        }
        
        for(int j=0; j<store.length; j++){
            int carry = store[j]/10;
            int value = store[j]%10;
            if(store.length>j+1){
                store[j+1] += carry;
            }
            mul+= value;
        }
        mul = new StringBuffer(mul).reverse().toString();
        
        if(this.sign!=h.sign){
            mul = "-" + mul;
        }
        
        if(h.hugeInt.charAt(0) == '0'){
            mul = "0";
        }
        
        return new HugeInteger(mul);
    }
    
    public HugeInteger divide(HugeInteger h){
        String div ="0";
        HugeInteger remain = this.absolute();
        HugeInteger remain2 = h.absolute();
        int increment = 0;
        
        if(h.hugeInt.charAt(0) == '0'){
            throw new StringIndexOutOfBoundsException("Can't divide by 0");
        }
        
        else if(this.compareTo(h)==0){
            div = "1";
        }
        
        else if(this.absolute().compareTo(h.absolute())==-1){
            div= "0";
        }
        
        else {
            while (remain.compareTo(remain2)==1 || remain.compareTo(remain2)==0){
                increment++;
                remain = remain.subtract(remain2);
            }
            div= String.valueOf(increment);
            if(this.sign!=h.sign){
            div = "-" + div;
            }
        }
        
        return new HugeInteger(div);
    }
    
    public int compareTo(HugeInteger h){
        if (this.sign!=h.sign){
            return (this.sign ? 1 : -1);
        }

        else if(this.sign==h.sign){
            if(this.size>h.size){
                return (this.sign ? 1 : -1);
            }
            else if(this.size<h.size){
                return (this.sign ? -1 : 1);
            }
            else if (this.size==h.size){
            for(int i=0; i<this.size; i++){
                if (hugeInt.charAt(i) < h.hugeInt.charAt(i)){
                    return (this.sign ? -1 : 1);
                    }
                else if (hugeInt.charAt(i) > h.hugeInt.charAt(i)){
                    return (this.sign ? 1 : -1);
                    }
                }
            }
        }
     
        return 0;
    }
    
    public HugeInteger absolute(){
        HugeInteger absolute = new HugeInteger(hugeInt);
        absolute.sign=true;
        return absolute;
    }
}