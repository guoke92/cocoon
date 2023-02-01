package io.cocoon.exception;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-09-24 16:27
 **/
public interface IAssert {

     BaseException newException(Object...args);

     BaseException newException(Throwable throwable, String... args);

     default <T> void isTrue(T obj, Predicate<T> predicate, Object... args){
          if(predicate.test(obj)){
               throw newException(args);
          }
     }

     default <T> void isTrue(T obj, Predicate<T> predicate, @NotNull Supplier<? extends Objects> messageSupplier){
          if(predicate.test(obj)){
               throw newException(SupplierUtil.nullSafeGet(messageSupplier));
          }
     }

     default void isTrue(boolean expression, Object... args){
          if(!expression){
              throw newException(args);
          }
     }

     default void isTrue(boolean expression, @NotNull Supplier<? extends Objects> messageSupplier){
          if(!expression){
               throw newException(SupplierUtil.nullSafeGet(messageSupplier));
          }
     }

     default void notNull(Object obj, Object... args){
          this.isTrue(obj, Objects::isNull, args);
     }

     default void isNull(Object obj, Object... args){
          this.isTrue(obj, Objects::nonNull, args);
     }

     default void notBlank(String str, Object... args){
          this.isTrue(str, StringUtils::isBlank, args);
     }

     default void isBlank(String str, Object... args){
          this.isTrue(str, StringUtils::isNotBlank, args);
     }

     default void notEmpty(String str, Object... args){
          this.isTrue(str, StringUtils::isEmpty, args);
     }

     default void isEmpty(String str, Object... args){
          this.isTrue(str, StringUtils::isNotEmpty, args);
     }

     default void notEmpty(Collection<?> collection, Object... args){
          this.isTrue(collection, CollectionUtils::isEmpty, args);
     }

     default void isEmpty(Collection<?> collection, Object... args){
          this.isTrue(collection, CollectionUtils::isNotEmpty, args);
     }

     default void notEmpty(Map<?,?> map, Object... args){
          this.isTrue(map, CollectionUtils::isEmpty, args);
     }

     default void isEmpty(Map<?,?> map, Object... args){
          this.isTrue(map, CollectionUtils::isNotEmpty, args);
     }

     default void isInstanceOf(Object obj, Class<?> type, Object... args){
          Supplier<String> supplier;
          if(Objects.nonNull(args[0])){
               supplier = () -> args[0] + ", " + "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type;
          } else {
               supplier = () -> "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type;
          }
          this.isTrue(type.isInstance(obj), supplier);
     }

}