package br.com.midiadev.util;

/*
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class WrapperUtils {

	/**
	 * Verifica se o objeto (ou array de objeto) é nulo ou vazio.<br />
	 * <blockquote>
	 * Se o objeto for uma collection irá validar apenas o primeiro item da lista.<br />
	 * Se o primeiro objeto do array for nulo ou vazio não valida os seguintes.
	 * </blockquote>
	 * 
	 * @author <a href="mailto:paulohenriqueas13@gmail.com">Paulo H3nrique Alves</a>
	 * @param object
	 * @return
	 */
	public static boolean isNullOrEmpty(Object... object) {
		Boolean ret = Boolean.FALSE;
		
		if(object == null || object.length == 0){
			return Boolean.TRUE;
		}
		List<Object> objList = Arrays.asList(object);
		
		for (Object obj : objList) {
			if(ret) {
				return Boolean.TRUE;
			}
			if(obj != null) {
				if(obj instanceof Collection<?>) {
					Collection<?> list = (Collection<?>) obj;
					if(list.isEmpty()) {
						ret = Boolean.TRUE;
					} else {
						return isNullOrEmpty(list.toArray()[0]);
					}
				} else {
					if(String.valueOf(obj).trim().isEmpty()) {
						ret = Boolean.TRUE;
					}
				}
			} else {
				ret = Boolean.TRUE;
			}
		}
		return ret;
	}

}