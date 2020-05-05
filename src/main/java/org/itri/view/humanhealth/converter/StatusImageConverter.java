package org.itri.view.humanhealth.converter;

import org.itri.view.humanhealth.dao.Status;
import org.zkoss.admin.ecommerce.dao.Type;
import org.zkoss.bind.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Image;

import java.io.IOException;
import java.util.*;

/**
 * Convert {@link Type} to a font awesome icon class
 */
public class StatusImageConverter implements Converter<String, Type, Component> {


	@Override
	public byte[] coerceToBean(AImage compAttr, Image component, BindContext ctx) {
		
		return compAttr.getByteData();
	}

	@Override
	public AImage coerceToUi(byte[] beanProp, Image component, BindContext ctx) {
		try {
			if (beanProp != null && beanProp.length > 0) {
				AImage im = new AImage("", beanProp);
				component.setContent(im);
				return im;
			}
			
			return null;
		} catch (IOException e) {
			
			return null;
		}
	}
}
