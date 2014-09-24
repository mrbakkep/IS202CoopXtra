package no.uia.slit.auth;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author even
 */
@FacesConverter(value="authGroupConverter", forClass=AuthGroup.class)
public class AuthGroupConverter implements Converter {

   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value) {
      return AuthGroup.valueOf(value);
   }

   @Override
   public String getAsString(FacesContext context, UIComponent component, Object value) {
      if (value instanceof AuthGroup) {
         return ((AuthGroup)value).toString();
      }
      else {
         throw new ConverterException("AuthGroupConveter can't convert "+value+"!");
      }
   }

}
