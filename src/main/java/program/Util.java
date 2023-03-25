package program;

import java.util.*;
import java.util.stream.Collectors;

public class Util {
    private Util() {
    }

    public static <T> List<T> checkParam(List<T> param) {
        Objects.requireNonNull(param, "매개변수가 null입니다.");

        if (param.isEmpty()) {
            throw new IllegalArgumentException("매개변수가 비었습니다.");
        }
        // 요소가 empty인 것을 체크하는 이유는 무엇인가요
        param.forEach(p -> Objects.requireNonNull(p));

        // 정책에 따라 중복이면 1. 중복을 제거한다. 2. 예외처리한다. 3. 허용한다.
        param = param.stream().distinct().collect(Collectors.toList());

        // 자료가 깊은 복사가 진행되었는지? 어떻게 체크하는지? 체크하기 보단 깊은 복사또는 방어적 복사를 진행하는 것이 좋아보입니다.
        List<T> checkedParam = List.copyOf(param);
        return checkedParam;
    }

    public static <T> Set<T> checkParam(Set<T> param) {
        Objects.requireNonNull(param, "매개변수가 null입니다.");

        if (param.isEmpty()) {
            throw new IllegalArgumentException("매개변수가 비었습니다.");
        }

        param.forEach(p -> Objects.requireNonNull(p,"매개변수의 요소가 null입니다."));
        //set은 중복체크가 필요없다.

        Set<T> checkedParam = Set.copyOf(param);

        return checkedParam;
    }

    private static <K,V> Map<K,V> checkParam(Map<K,V>  param) {
        Objects.requireNonNull(param, "매개변수가 null입니다.");

        if (param.isEmpty()) {
            throw new IllegalArgumentException("매개변수가 비었습니다.");
        }

        for (Map.Entry<K, V> entry : param.entrySet()) {
            Objects.requireNonNull(entry.getKey(), "매개변수의 키가 null입니다.");
            Objects.requireNonNull(entry.getValue(), "매개변수의 값이 null입니다.");
        }
        // map은 중복체크가 필요한가?
        // key는 중복체크 필요없다
        // value는 중복체크 필요없어 보인다.

        Map<K,V> checkedParam = Map.copyOf(param);

        return checkedParam;
    }

    private static <T> void isReadOnly(Collection<T> param) throws UnsupportedOperationException {
        //   return param.getClass().getName().contains("Unmodifiable");
        param.add(null);
    }

    private static <T> boolean isDeepCopied(Collection<T> param) {
        return false;
    }

    private static <T> Collection<T> makeDeepCopied(Collection<T> param) {
        return param;
    }

    private static <T> List<T> makeDeepCopied(List<T> param) {

        return param;
    }

    private static <T> Set<T> makeDeepCopied(Set<T> param) {
        return param;
    }

    private static <T> Collection<T> makeReadOnly(Collection<T> param) {
        return Collections.unmodifiableCollection(param);
    }

    private static <T> List<T> makeReadOnly(List<T> param) {
        return Collections.unmodifiableList(param);
    }

    private static <T> Set<T> makeReadOnly(Set<T> param) {
        return Collections.unmodifiableSet(param);
    }
}