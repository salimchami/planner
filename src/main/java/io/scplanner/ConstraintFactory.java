package io.scplanner;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public interface ConstraintFactory<S, F, P> {

    ConstraintFactory<S, F, P> name(String constraintName);

    ConstraintFactory<S, F, P> withFact(Class<F> factClass);

    ConstraintFactoryImpl<S, F, P> filter(ConstraintFilter<F, List<P>> consumer);

    Constraint<S, F, P> apply(ScoreLevel score, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction);


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
