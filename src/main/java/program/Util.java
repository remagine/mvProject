package program;

import java.util.*;

public class Util {
    public static <T> List<T> checkParam(List<T> param) {

        Objects.requireNonNull(param, "매개변수가 null입니다.");

        if (param.isEmpty()) {
            throw new IllegalArgumentException("매개변수가 비었습니다.");
        }
        List<T> checkedParam = List.copyOf(param);
        checkedParam = makeReadOnly(checkedParam);

        return checkedParam;
    }

    public static <T> Set<T> checkParam(Set<T> param) {

        Objects.requireNonNull(param, "매개변수가 null입니다.");

        if (param.isEmpty()) {
            throw new IllegalArgumentException("매개변수가 비었습니다.");
        }
        Set<T> checkedParam = Set.copyOf(param);
        checkedParam = makeReadOnly(checkedParam);

        return checkedParam;
    }

    /*private static Map checkParam(Map param) {
        Map checkedParam = Map.copyOf(param);
        Objects.requireNonNull(param, "매개변수가 null입니다.");

        if (param.isEmpty()) {
            throw new IllegalArgumentException("매개변수가 비었습니다.");
        }
        checkedParam = makeDeepCopied(checkedParam);
        checkedParam = makeReadOnly(checkedParam);

        return checkedParam;
    }*/

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