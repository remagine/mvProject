package program;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Util {
    public static <T> Collection checkDataParam(Collection<T> param){
        Collection<T> checkedParam = null;
        Objects.requireNonNull(param, "매개변수가 null입니다.");

        if(param.isEmpty()){
            throw new IllegalArgumentException("매개변수가 비었습니다.");
        }

        // 파라미터를 깊은복사처리해주는 것이 오히려 좋아보임
        /*try {
            isReadOnly(param);
        } catch (UnsupportedOperationException e){
            throw new IllegalArgumentException("읽기전용 파라미터가 아닙니다.");
        }*/

        // readOnly를 체크하기 보다 아예 readOnly화 시켜주는 게 좋아보임


        return checkedParam;
    }

    private static void isReadOnly(Collection param) throws UnsupportedOperationException{
        //   return param.getClass().getName().contains("Unmodifiable");
        param.add(null);
    }

    private static boolean isDeepCopied(Collection param) {
        return false;
    }

    private static Collection makeDeepCopied(Collection param){



        return param;
    }

    private static Collection makeReadOnly(Collection param){
        return Collections.unmodifiableCollection(param);
    }


}