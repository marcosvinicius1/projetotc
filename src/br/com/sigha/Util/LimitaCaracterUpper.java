/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author MarcosVinicius
 */
public class LimitaCaracterUpper extends PlainDocument {

    private int iMaxLength;

    public LimitaCaracterUpper(int maxlen) {
        super();
        iMaxLength = maxlen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (iMaxLength <= 0) // aceitara qualquer no. de caracteres  
        {
            super.insertString(offset, str.toUpperCase(), attr);
            return;
        }

        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLength) // se o comprimento final for menor...  
        {
            super.insertString(offset, str.toUpperCase(), attr);   // ...aceita str  
        }
    }

}
