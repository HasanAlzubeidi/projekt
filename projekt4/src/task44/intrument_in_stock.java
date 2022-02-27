/*
 * The MIT License (MIT)
 * Copyright (c) 2020 Leif Lindbäck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction,including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so,subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package task44;

public class intrument_in_stock  {

    private int  instrument_id;
    private String type_of_instrument;
    private String brand;
    private String number_of_pcs_in_stock;
    private int fee;

   


    
    
   
    public intrument_in_stock(int  instrument_id, String type_of_instrument, String brand, String number_of_pcs_in_stock, int fee )
    {
    	
        this.instrument_id=instrument_id;
        this.type_of_instrument =type_of_instrument;
        this.brand=brand;
        this.number_of_pcs_in_stock=number_of_pcs_in_stock;
        this.fee=fee;

    }
	











	public int getInstrument_id()
    {
        return instrument_id;
    }

    public String getType_of_instrument()
    {
        return type_of_instrument;
    }

     public int getFee() {
         return fee;
     }

     public String getNumber_of_pcs_in_stock()
     {
         return number_of_pcs_in_stock;
     }

     public String getBrand() {
         return brand;
     }

     public String toString() {
         StringBuilder stringRepresentation = new StringBuilder();
         stringRepresentation.append("instrument_in_stock: [");
         stringRepresentation.append("instrument_id: ");
         stringRepresentation.append(instrument_id);
         stringRepresentation.append(", type_of_instrument: ");
         stringRepresentation.append(type_of_instrument);
         stringRepresentation.append(", brand: ");
         stringRepresentation.append(brand);
         stringRepresentation.append(", number_of_pcs_in_stock : ");
         stringRepresentation.append(number_of_pcs_in_stock);
         stringRepresentation.append(", fee: ");
         stringRepresentation.append(fee);
         stringRepresentation.append("]");
         return stringRepresentation.toString();
     }
 }


