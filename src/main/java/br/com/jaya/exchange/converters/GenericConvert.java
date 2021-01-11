
package br.com.jaya.exchange.converters;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public class GenericConvert {

     public static <E, T> E convertModelMapper(T source, Class<E> typeDestination, MatchingStrategy strategy) {

          E model = null;
          if (source != null && typeDestination != null) {

               ModelMapper modelMapper = new ModelMapper();

               modelMapper.getConfiguration().setMatchingStrategy(strategy);
               model = modelMapper.map(source, typeDestination);
          }

          return model;
     }

     public static <E, T> E convertModelMapper(T source, Class<E> typeDestination) {

          return convertModelMapper(source, typeDestination, MatchingStrategies.STRICT);
     }
}
