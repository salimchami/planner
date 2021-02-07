package io.scplanner;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface ConstraintFactory {

    <F> ConstraintFactory withFact(Class<F> factClass);

    <P> ConstraintFactory fromMultiple(Class<P> planningVariableClass);

    <F, P> ConstraintFactory filter(Function<F, P> filter);

    <P> ConstraintFactory favorableScore(UnaryOperator<P> favorableScore);

    <F, P> Constraint<F, P> apply(String constraintName, ScoreLevel score, UnaryOperator<F> fact, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction);


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
