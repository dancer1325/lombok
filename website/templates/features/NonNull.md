* allows
  * about NullPointerException
    * stop worrying
    * love it
* history
  * | lombok v0.11.10
    * introduced
* overview
  * TODO:
            <p>
                You can use <code>@NonNull</code> on a record component, or a parameter of a method or constructor. This will cause to lombok generate a null-check statement for you.
            </p><p>
                Lombok has always treated various annotations generally named <code>@NonNull</code> on a field as a signal to generate a null-check if lombok generates an entire method or constructor for you, via for example <a href="/features/Data"><code>@Data</code></a>. However, using lombok's own <code>@lombok.NonNull</code> on a parameter or record component results in the insertion of the null-check at the top of that method.
            </p><p>
                The null-check looks like <code>if (param == null) throw new NullPointerException("param is marked non-null but is null");</code> and will be inserted at the very top of your method. For constructors, the null-check will be inserted immediately following any explicit <code>this()</code> or <code>super()</code> calls. For record components, the null-check will be inserted in the 'compact constructor' (the one that has no argument list at all), which will be generated if you have no constructor. If you have written out the record constructor in long form (with parameters matching your components exactly), then nothing happens - you'd have to annotate the parameters of this long-form constructor instead.
            </p><p>
                If a null-check is already present at the top, no additional null-check will be generated.
            </p>
        </@f.overview>

        <@f.snippets name="NonNull" />

        <@f.confKeys>
            <dt>
                <code>lombok.nonNull.exceptionType</code> = [<code>NullPointerException</code> | <code>IllegalArgumentException</code> | <code>JDK</code> | <code>Guava</code> | <code>Assertion</code>] (default: <code>NullPointerException</code>).
            </dt><dd>
                When lombok generates a null-check <code>if</code> statement, by default, a <code>java.lang.NullPointerException</code> will be thrown with '<em>field name</em> is marked non-null but is null' as the exception message. However, you can use <code>IllegalArgumentException</code> in this configuration key to have lombok throw that exception with this message instead. By using <code>Assertion</code>, an <code>assert</code> statement with the same message will be generated. The keys <code>JDK</code> or <code>Guava</code> result in an invocation to the standard nullcheck method of these two frameworks: <code>java.util.Objects.requireNonNull([field name here], "[field name here] is marked non-null but is null");</code> or <code>com.google.common.base.Preconditions.checkNotNull([field name here], "[field name here] is marked non-null but is null");</code> respectively.
            </dd><dt>
                <code>lombok.nonNull.flagUsage</code> = [<code>warning</code> | <code>error</code>] (default: not set)
            </dt><dd>
                Lombok will flag any usage of <code>@NonNull</code> as a warning or error if configured.
            </dd>
        </@f.confKeys>

        <@f.smallPrint>
            <p>
                Lombok's detection scheme for already existing null-checks consists of scanning for if statements or assert statements that look just like lombok's own. Any 'throws' statement as the 'then' part of the if statement, whether in braces or not, counts. Any invocation to any method named <code>requireNonNull</code> or <code>checkNotNull</code> counts. The conditional of the if statement <em>must</em> look exactly like <code>PARAMNAME == null</code>; the assert statement <em>must</em> look exactly like <code>PARAMNAME != null</code>. The invocation to a <code>requireNonNull</code>-style method must be on its own (a statement which just invokes that method), or must be the expression of an assignment or variable declaration statement. The first statement in your method that is not such a null-check stops the process of inspecting for null-checks.
            </p><p>
                While <code>@Data</code> and other method-generating lombok annotations will trigger on various well-known annotations that signify the field must never be <code>@NonNull</code>, this feature only triggers on lombok's own <code>@NonNull</code> annotation from the <code>lombok</code> package.
            </p><p>
                A <code>@NonNull</code> on a primitive parameter results in a warning. No null-check will be generated.
            </p><p>
                A <code>@NonNull</code> on a parameter of an abstract method used to generate a warning; starting with version 1.16.8, this is no longer the case, to acknowledge the notion that <code>@NonNull</code> also has a documentary role. For the same reason, you can annotate a method as <code>@NonNull</code>; this is allowed, generates no warning, and does not generate any code.
            </p>
        </@f.smallPrint>
    </@f.scaffold>
