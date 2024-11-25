* allow
  * variables
    * 👀mutable 👀
    * local
* history
  * | lombok 1.16.12
    * introduced -- as -- experimental feature
  * | lombok 1.16.20
    * -- promoted to -- stable feature
      * ALTHOUGH, it's controversial
    * -- based on -- [JEP 286](http://openjdk.java.net/jeps/286)
      
* overview
  * TODO:
            <p>
                <code>var</code> works exactly like <a href="/features/val"><code>val</code></a>, except the local variable is <em>not</em> marked as <code>final</code>.
            </p><p>
                The type is still entirely derived from the mandatory initializer expression, and any further assignments, while now legal (because the variable is no longer <code>final</code>), aren't looked at to determine the appropriate type.<br />
                For example, <code>var x = "Hello"; x = Color.RED;</code> does <em>not</em> work; the type of x will be inferred to be <code>java.lang.String</code> and thus, the <code>x = Color.RED</code> assignment will fail. If the type of <code>x</code> was inferred to be <code>java.lang.Object</code> this code would have compiled, but that's not how<code>var</code> works.
            </p>
        </@f.overview>

        <@f.confKeys>
            <dt>
                <code>lombok.var.flagUsage</code> = [<code>warning</code> | <code>error</code>] (default: not set)
            </dt><dd>
                Lombok will flag any usage of <code>var</code> as a warning or error if configured.
            </dd>
        </@f.confKeys>
    </@f.scaffold>
