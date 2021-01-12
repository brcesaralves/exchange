
package br.com.jaya.exchange.util;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Pageable extends PageRequest {

     private static final long serialVersionUID = 2733483641029787352L;

     public static final int PAGE_DEFAULT = 0;

     public static final int LIMIT_DEFAULT = 10;

     private Pageable(Integer page, Integer size, Sort sort) {

          super(page, size, sort);
     }

     private Pageable(Integer page, Integer limit) {

          super(page, limit, Sort.unsorted());
     }

     public static Pageable setPageable(Integer page, Integer limit) {

          if (Objects.isNull(page)) {
               page = PAGE_DEFAULT;
          }

          if (Objects.isNull(limit) || limit >= LIMIT_DEFAULT) {
               limit = LIMIT_DEFAULT;
          }

          return new Pageable(page, limit);
     }
}
