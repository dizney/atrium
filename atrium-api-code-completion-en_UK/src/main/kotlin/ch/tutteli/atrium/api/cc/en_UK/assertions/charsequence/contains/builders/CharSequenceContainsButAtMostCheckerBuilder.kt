package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilderBase

/**
 * Represents the builder of the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied to the input of the search.
 *
 * @constructor Represents the builder of the second step of a `contains at least but at most` check within the
 *              fluent API of a sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
open class CharSequenceContainsButAtMostCheckerBuilder<T : CharSequence, D : IDecorator>(
    times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilder<T, D>,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsButAtMostCheckerBuilderBase<T, D>(
    times,
    atLeastBuilder,
    containsBuilder,
    containsBuilder.plant::containsNot.name,
    containsBuilder::atMost.name,
    containsBuilder::atLeast.name,
    atLeastBuilder::butAtMost.name,
    containsBuilder::exactly.name
)