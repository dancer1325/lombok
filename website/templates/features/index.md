# Lombok features
* `val` 
  * see [val](val.md)
* `var`
  * see [var](var.md)
* `@NonNull`
  * see [@NonNull](NonNull.md)
* TODO:

                <@main.feature title="@Cleanup" href="Cleanup">
                    Automatic resource management: Call your <code>close()</code> methods safely with no hassle.
                </@main.feature>

                <@main.feature title="@Getter/@Setter" href="GetterSetter">
                    Never write <code>public int getFoo() {return foo;}</code> again.
                </@main.feature>

                <@main.feature title="@ToString" href="ToString">
                    No need to start a debugger to see your fields: Just let lombok generate a <code>toString</code> for
                    you!
                </@main.feature>

                <@main.feature title="@EqualsAndHashCode" href="EqualsAndHashCode">
                    Equality made easy: Generates <code>hashCode</code> and <code>equals</code> implementations from the
                    fields of your object..
                </@main.feature>

                <@main.feature title="@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor" href="constructor">
                    Constructors made to order: Generates constructors that take no arguments, one argument per final /
                    non-nullfield, or one argument for every field.
                </@main.feature>

                <@main.feature title="@Data" href="Data">
                    All together now: A shortcut for <code>@ToString</code>, <code>@EqualsAndHashCode</code>,
                    <code>@Getter</code> on all fields, and <code>@Setter</code> on all non-final fields, and
                    <code>@RequiredArgsConstructor</code>!
                </@main.feature>

                <@main.feature title="@Value" href="Value">
                    Immutable classes made very easy.
                </@main.feature>

* `@Builder`
  * see [@Builder](Builder.md)
* `@SneakyThrows`
  * TODO: " href="SneakyThrows">
                        To boldly throw checked exceptions where no one has thrown them before!
                    </@main.feature>

                    <@main.feature title="@Synchronized" href="Synchronized">
                        <code>synchronized</code> done right: Don't expose your locks.
                    </@main.feature>

                    <@main.feature title="@Locked" href="Locked">
                        Pop it and <strong>lock</strong> it! <code>ReentrantLock</code>, now with less hassle.
                    </@main.feature>
			
                    <@main.feature title="@With" href="With">
                        Immutable 'setters' - methods that create a clone but with one changed field.
                    </@main.feature>

                    <@main.feature title="@Getter(lazy=true)" href="GetterLazy">
                        Laziness is a virtue!
                    </@main.feature>

                    <@main.feature title="@Log" href="log">
                        Captain's Log, stardate 24435.7: &quot;What was that line again?&quot;
                    </@main.feature>
			
                    <@main.feature title="experimental" href="/features/experimental/all">
                        Head to the lab: The new stuff we're working on.
                    </@main.feature>
                </div>

# Configuration system
* 1! configuration -- for -- ALL your
  * entire project or
  * workspace
* see [configuration](configuration.md)

# Running delombok
* Delombok
  * your source files -- are copied to -- another directory / ALL lombok annotations are replaced
    * _Example:_ `@Getter` -- is replaced by -- actual getter / NO annotation
    * uses
      * check out final code
      * if you want to stop using lombok & use final source code
  * see [delombok](delombok.md)

