package io.edukativ.myskoolin.planner;

import java.util.function.Function;

public interface ConstraintFactory {

    <A> ConstraintFactory withFact(Class<A> factClass);


    <P> ConstraintFactory fromMultiple(Class<P> planningVariableClass);

    <A, R> ConstraintFactory filter(Function<A, R> filter);

    <A, P> Constraint<A, P> apply(String constraintName, ScoreLevel score, PenaltyFunction<A, P> penaltyFunction);


//    <A> UniConstraintStream<A> from(Class<A> fromClass);
//
//    <A> UniConstraintStream<A> fromUnfiltered(Class<A> fromClass);
//
//    default <A> BiConstraintStream<A, A> fromUniquePair(Class<A> fromClass) {
//        return fromUniquePair(fromClass, new NoneBiJoiner<>());
//    }
//
//    <A> BiConstraintStream<A, A> fromUniquePair(Class<A> fromClass, BiJoiner<A, A> joiner);
//
//    default <A> BiConstraintStream<A, A> fromUniquePair(Class<A> fromClass, BiJoiner<A, A> joiner1, BiJoiner<A, A> joiner2) {
//        return fromUniquePair(fromClass, AbstractBiJoiner.merge(joiner1, joiner2));
//    }
//
//    default <A> BiConstraintStream<A, A> fromUniquePair(Class<A> fromClass, BiJoiner<A, A> joiner1, BiJoiner<A, A> joiner2,
//                                                        BiJoiner<A, A> joiner3) {
//        return fromUniquePair(fromClass, AbstractBiJoiner.merge(joiner1, joiner2, joiner3));
//    }
//
//    default <A> BiConstraintStream<A, A> fromUniquePair(Class<A> fromClass, BiJoiner<A, A> joiner1, BiJoiner<A, A> joiner2,
//                                                        BiJoiner<A, A> joiner3, BiJoiner<A, A> joiner4) {
//        return fromUniquePair(fromClass, AbstractBiJoiner.merge(joiner1, joiner2, joiner3, joiner4));
//    }
//
//    default <A> BiConstraintStream<A, A> fromUniquePair(Class<A> fromClass, BiJoiner<A, A>... joiners) {
//        return fromUniquePair(fromClass, AbstractBiJoiner.merge(joiners));
//    }
}
