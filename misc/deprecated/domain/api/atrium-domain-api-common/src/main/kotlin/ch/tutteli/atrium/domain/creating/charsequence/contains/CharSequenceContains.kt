//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.charsequence.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.basic.contains.Contains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated [CharSequence] `contains` [Assertion] builders.
 *
 * The building process is typically started by the creation of a [Builder],
 * goes on by specifying a desired [SearchBehaviour],
 * defines which [Checker]s should be applied and
 * is finalized by one of the [CharSequenceContainsAssertions]
 * which usually use a [Creator] which in turn use a [Searcher].
 */
@Deprecated("Use the CharSequenceContains from atrium-logic; will be removed with 1.0.0")
interface CharSequenceContains {

    /**
     * The entry point of the contract, containing the [subjectProvider] -- i.e. the subject of the assertion
     * for which the sophisticated `contain` assertion should be created -- as well as the chosen [searchBehaviour].
     *
     * The [searchBehaviour] might me modified in which case it is recommended that a new [Builder] is created (retain
     * immutability).
     */
    interface Builder<out T : CharSequence, out S : SearchBehaviour> : Contains.Builder<T, S>

    /**
     * The step of choosing/defining [Checker]s.
     */
    interface CheckerOption<out T : CharSequence, out S : SearchBehaviour>
        : Contains.CheckerOption<T, S, Checker, Builder<T, S>>

    /**
     * Represents a search behaviour but leaves it up to the [Searcher] how this behaviour is implemented -- yet, it
     * provides a method to decorate a description (a [Translatable]) in order that it reflects the search behaviour.
     */
    interface SearchBehaviour : Contains.SearchBehaviour

    /**
     * Represents the final step of a sophisticated `contains` assertion builder which creates the [AssertionGroup]
     * as such.
     *
     * @param T The type of the suject of the assertion.
     * @param SC The type of the search criteria.
     */
    interface Creator<in T : CharSequence, in SC> : Contains.Creator<T, SC>

    /**
     * Represents a check for the search result such as: the object is contained exactly once in the input of the
     * search.
     *
     * It provides the method [createAssertion] which creates an [Assertion] representing this check.
     */
    interface Checker : Contains.Checker

    /**
     * Represents a searcher which supports the search behaviour [S] for a given input [CharSequence] of the search.
     *
     * @param S The search behaviour which should be applied to the input [CharSequence] in which the [Searcher]
     *   will look for something -- the actual implementation of the search behaviour happens in the
     *   [Searcher]; [SearchBehaviour] only decorates the [Translatable] for reporting.
     * @param SC The search criterion
     */
    interface Searcher<S : SearchBehaviour, SC> {
        /**
         * Searches in the given [searchIn] for the given [searchFor], using its [toString][Any.toString]
         * implementation, and returns the number of occurrences.
         *
         * Whether searches are disjoint or non-disjoint is up to the implementation.
         *
         * @param searchIn The input [CharSequence] in which this [Searcher] shall search
         * @param searchFor The object which shall be found
         *
         * @return The number of occurrences of [searchFor] in [searchIn].
         */
        fun search(searchIn: CharSequence, searchFor: SC): Int
    }
}

