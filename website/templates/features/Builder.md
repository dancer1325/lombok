* allows
  * API is
    * easy-to-use, 
    * powerful,
    * feature-richfor
* history
  * | lombok v0.12.0
    * introduced -- as -- experimental feature 
  * | lombok v1.16.0
    * promoted -- to -- main lombock package
    * `@Builder` -- gained -- `@Singular` support
  * | lombok v1.16.8
    * `@Builder` + `@Singular` -- adds a -- clear method
  * | lombok v1.16.16
    * `@Builder.Default` was added
  * | lombok v1.18.8.
    * `@Builder(builderMethodName = "")`
      * legal
      * -- suppress -- generation of the builder method
    * `@Builder(access = AccessLevel.PACKAGE)`
      * legal
      * -- generate with the indicated access level -- the
        * builder class,
        * builder method
* overview 
  * -- produces -- complex builder APIs / your classes

    ```
    Person.builder()
    .name("Adam Savage")
    .city("San Francisco")
    .job("Mythbusters")
    .job("Unchained Reaction")
    .build();
    ```
    
* uses
  * | 
    * class,
    * constructor,
    * method
      * LESS common
      * target
        * := method / annotated with `@Builder`
      * -> generate
        * inner static class /
          * named `ClassNameBuilder`
          * 's type arguments == static method's type arguments (called `builder`)
          * NOT allowed to add aditional Lombok annotations | this generated class
          
            ```
            @Builder
            public class Person {
                private String name;
                private int age;
            
                @EqualsAndHashCode // ❌ Not allowed on the builder class!
                public static class PersonBuilder {
                }
            }
            ```

        * | `builder`
          * 1 private non-static non-final field / EACH `target`'s parameter 
          * constructor
            * package private
            * no-args empty 
          * 'setter'-like method / EACH `target`'s parameter 
            * type == parameter's type
            * name  == parameter's name
            * returns the builder itself
              * -> setter calls -- can be -- chained
          * `build()`  
            * returned type == `target` returned type
          * `toString()` implementation
        * | class / contain the `target`
          * `builder()`</code> method
            * creates a new instance of the `builder`
        * / 
          * 👀if that element (of the previous ones) ALREADY exists (looking ONLY at names) -> element will be silently skipped 👀
            * ALSO the `builder` itself
              * == if that class ALREADY exists & 
                * fields & methods NO exist | it -> they will be injected | this ALREADY EXISTING class
                * fields & methods ALREADY exist -> NO injected
      * 'singular' methods
        * := method / 
          * take 1 element
          * add the element | list
        * allows
          * collecting parameters/fields
        
        ```
        Person.builder()
         .job("Mythbusters")
         .job("Unchained Reaction")
         .build();
        ```

* TODO:
                                      <p>would result in the <code>List&lt;String&gt; jobs</code> field to have 2 strings in it. To get this behavior, the field/parameter needs to be annotated with <code>@Singular</code>. The feature has <a href="#singular">its own documentation</a>.
                                      </p><p>
                                          Now that the "method" mode is clear, putting a <code>@Builder</code> annotation on a constructor functions similarly; effectively, constructors are just static methods that have a special syntax to invoke them: Their 'return type' is the class they construct, and their type parameters are the same as the type parameters of the class itself.
                                      </p><p>
                                          Finally, applying <code>@Builder</code> to a class is as if you added <code>@AllArgsConstructor(access = AccessLevel.PACKAGE)</code> to the class and applied the <code>@Builder</code> annotation to this all-args-constructor. This only works if you haven't written any explicit constructors yourself or allowed lombok to create one such as with <code>@NoArgsConstructor</code>. If you do have an explicit constructor, put the <code>@Builder</code> annotation on the constructor instead of on the class. Note that if you put both `@Value` and `@Builder` on a class, the package-private constructor that `@Builder` wants to generate 'wins' and suppresses the constructor that `@Value` wants to make.
                                      </p><p>
                                          If using <code>@Builder</code> to generate builders to produce instances of your own class (this is always the case unless adding <code>@Builder</code> to a method that doesn't return your own type), you can use <code>@Builder(toBuilder = true)</code> to also generate an instance method in your class called <code>toBuilder()</code>; it creates a new builder that starts out with all the values of this instance. You can put the <code>@Builder.ObtainVia</code> annotation on the parameters (in case of a constructor or method) or fields (in case of <code>@Builder</code> on a type) to indicate alternative means by which the value for that field/parameter is obtained from this instance. For example, you can specify a method to be invoked: <code>@Builder.ObtainVia(method = "calculateFoo")</code>.
                                      </p><p>
                                          The name of the builder class is <code><em>Foobar</em>Builder</code>, where <em>Foobar</em> is the simplified, title-cased form of the return type of the <em>target</em> - that is, the name of your type for <code>@Builder</code> on constructors and types, and the name of the return type for <code>@Builder</code> on methods. For example, if <code>@Builder</code> is applied to a class named <code>com.yoyodyne.FancyList&lt;T&gt;</code>, then the builder name will be <code>FancyListBuilder&lt;T&gt;</code>. If <code>@Builder</code> is applied to a method that returns <code>void</code>, the builder will be named <code>VoidBuilder</code>.
                                      </p><p>
                                          The configurable aspects of builder are:
                                          <ul>
                                              <li>
                                                  The <em>builder's class name</em> (default: return type + 'Builder')
                                              </li><li>
                                                  The <em>build()</em> method's name (default: <code>"build"</code>)
                                              </li><li>
                                                  The <em>builder()</em> method's name (default: <code>"builder"</code>)
                                              </li><li>
                                                  If you want <code>toBuilder()</code> (default: no)
                                              </li><li>
                                                  The access level of all generated elements (default: <code>public</code>).
                                              </li><li>
                                                  (discouraged) If you want your builder's 'set' methods to have a prefix, i.e. <code>Person.builder().setName("Jane").build()</code> instead of <code>Person.builder().name("Jane").build()</code> and what it should be.
                                              </li>
                                          </ul>
                                          Example usage where all options are changed from their defaults:<br />
                                          <code>@Builder(builderClassName = "HelloWorldBuilder", buildMethodName = "execute", builderMethodName = "helloWorld", toBuilder = true, access = AccessLevel.PRIVATE, setterPrefix = "set")</code><br />
                                      </p><p>
                                          Looking to use your builder with <a href="https://github.com/FasterXML/jackson">Jackson</a>, the JSON/XML tool? We have you covered: Check out the <a href="/features/experimental/Jacksonized">@Jacksonized</a> feature.
                                      </p>
                                  </@f.overview>
	
                                  <@f.featureSection>
                                      <h3 id="builderdefault"><a name="builderdefault">@Builder.Default</a></h3>
		
                                      <p>
                                          If a certain field/parameter is never set during a build session, then it always gets 0 / <code>null</code> / false. If you've put <code>@Builder</code> on a class (and not a method or constructor) you can instead specify the default directly on the field, and annotate the field with <code>@Builder.Default</code>:<br />
                                          <code>@Builder.Default private final long created = System.currentTimeMillis();</code><br/>
                                          Calling Lombok-generated constructors such as <code>@NoArgsConstructor</code> will also make use of the defaults specified using <code>@Builder.Default</code> however explicit constructors will no longer use the default values and will need to be set manually or call a Lombok-generated constructor such as <code>this();</code> to set the defaults.
                                      </p>
                                  </@f.featureSection>
	
                                  <@f.featureSection>
                                      <h3 id="singular"><a name="singular">@Singular</a></h3>
		
                                      <p>
                                          By annotating one of the parameters (if annotating a method or constructor with <code>@Builder</code>) or fields (if annotating a class with <code>@Builder</code>) with the <code>@Singular</code> annotation, lombok will treat that builder node as a collection, and it generates 2 'adder' methods instead of a 'setter' method. One which adds a single element to the collection, and one which adds all elements of another collection to the collection. No setter to just set the collection (replacing whatever was already added) will be generated. A 'clear' method is also generated. These 'singular' builders are very complicated in order to guarantee the following properties:
                                          <ul>
                                              <li>
                                                  When invoking <code>build()</code>, the produced collection will be immutable.
                                              </li><li>
                                                  Calling one of the 'adder' methods, or the 'clear' method, after invoking <code>build()</code> does not modify any already generated objects, and, if <code>build()</code> is later called again, another collection with all the elements added since the creation of the builder is generated.
                                              </li><li>
                                                  The produced collection will be compacted to the smallest feasible format while remaining efficient.
                                              </li>
                                          </ul>
                                      </p><p>
                                          <code>@Singular</code> can only be applied to collection types known to lombok. Currently, the supported types are:
                                          <ul>
                                              <li>
                                                  <a href="https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html"><code>java.util</code></a>:
                                                  <ul>
                                                      <li>
                                                          <code>Iterable</code>, <code>Collection</code>, and <code>List</code> (backed by a compacted unmodifiable <code>ArrayList</code> in the general case).
                                                      </li><li>
                                                          <code>Set</code>, <code>SortedSet</code>, and <code>NavigableSet</code> (backed by a smartly sized unmodifiable <code>HashSet</code> or <code>TreeSet</code> in the general case).
                                                      </li><li>
                                                          <code>Map</code>, <code>SortedMap</code>, and <code>NavigableMap</code> (backed by a smartly sized unmodifiable <code>HashMap</code> or <code>TreeMap</code> in the general case).
                                                      </li>
                                                  </ul>
                                              </li><li>
                                                  <a href="https://github.com/google/guava">Guava</a>'s <code>com.google.common.collect</code>:
                                                  <ul>
                                                      <li>
                                                          <code>ImmutableCollection</code> and <code>ImmutableList</code> (backed by the builder feature of <code>ImmutableList</code>).
                                                      </li><li>
                                                          <code>ImmutableSet</code> and <code>ImmutableSortedSet</code> (backed by the builder feature of those types).
                                                      </li><li>
                                                          <code>ImmutableMap</code>, <code>ImmutableBiMap</code>, and <code>ImmutableSortedMap</code> (backed by the builder feature of those types).
                                                      </li><li>
                                                          <code>ImmutableTable</code> (backed by the builder feature of <code>ImmutableTable</code>).
                                                      </li>
                                                  </ul>
                                              </li>
                                          </ul>
                                      </p><p>
                                          If your identifiers are written in common english, lombok assumes that the name of any collection with <code>@Singular</code> on it is an english plural and will attempt to automatically singularize that name. If this is possible, the add-one method will use this name. For example, if your collection is called <code>statuses</code>, then the add-one method will automatically be called <code>status</code>. You can also specify the singular form of your identifier explicitly by passing the singular form as argument to the annotation like so: <code>@Singular("axis") List&lt;Line&gt; axes;</code>.<br />
                                          If lombok cannot singularize your identifier, or it is ambiguous, lombok will generate an error and force you to explicitly specify the singular name.
                                      </p><p>
                                          The snippet below does not show what lombok generates for a <code>@Singular</code> field/parameter because it is rather complicated. You can view a snippet <a href="builderSingular">here</a>.
                                      </p><p>
                                          If also using <code>setterPrefix = "with"</code>, the generated names are, for example, <code>withName</code> (add 1 name), <code>withNames</code> (add many names), and <code>clearNames</code> (reset all names).
                                      </p><p>
                                          Ordinarily, the generated 'plural form' method (which takes in a collection, and adds each element in this collection) will check if a <code>null</code> is passed the same way <a href="NonNull"><code>@NonNull</code></a> does (by default, throws a <code>NullPointerException</code> with an appropriate message). However, you can also tell lombok to ignore such collection (so, add nothing, return immediately): <code>@Singular(ignoreNullCollections = true</code>.
                                      </p>
                                  </@f.featureSection>

                                  <@f.featureSection>
                                      <h3 id="jackson"><a name="jackson">With Jackson</a></h3>
                                      <p>
                                          You can customize parts of your builder, for example adding another method to the builder class, or annotating a method in the builder class, by making the builder class yourself. Lombok will generate everything that you do not manually add, and put it into this builder class. For example, if you are trying to configure <a href="https://github.com/FasterXML/jackson">jackson</a> to use a specific subtype for a collection, you can write something like:<div class="snippet"><div class="java" align="left"><pre>
                              @Value @Builder
                              @JsonDeserialize(builder = JacksonExample.JacksonExampleBuilder.class)
                              public class JacksonExample {
                                  @Singular(nullBehavior = NullCollectionBehavior.IGNORE) private List&lt;Foo&gt; foos;
	
                                  @JsonPOJOBuilder(withPrefix = "")
                                  public static class JacksonExampleBuilder implements JacksonExampleBuilderMeta {
                                  }
	
                                  private interface JacksonExampleBuilderMeta {
                                      @JsonDeserialize(contentAs = FooImpl.class) JacksonExampleBuilder foos(List&lt;? extends Foo&gt; foos)
                                  }
                              }
	</@f.featureSection>
	
	<@f.snippets name="Builder" />

	<@f.confKeys>
		<dt>
			<code>lombok.builder.className</code> = [a java identifier with an optional star to indicate where the return type name goes] (default: <code>*Builder</code>)
		</dt><dd>
			Unless you explicitly pick the builder's class name with the <code>builderClassName</code> parameter, this name is chosen; any star in the name is replaced with the relevant return type.
		</dd><dt>
			<code>lombok.builder.flagUsage</code> = [<code>warning</code> | <code>error</code>] (default: not set)
		</dt><dd>
			Lombok will flag any usage of <code>@Builder</code> as a warning or error if configured.
		</dd><dt>
			<code>lombok.singular.useGuava</code> = [<code>true</code> | <code>false</code>] (default: false)
		</dt><dd>
			If <code>true</code>, lombok will use guava's <code>ImmutableXxx</code> builders and types to implement <code>java.util</code> collection interfaces, instead of creating implementations based on <code>Collections.unmodifiableXxx</code>. You must ensure that guava is actually available on the classpath and buildpath if you use this setting. Guava is used automatically if your field/parameter has one of the guava <code>ImmutableXxx</code> types.
		</dd><dt>
			<code>lombok.singular.auto</code> = [<code>true</code> | <code>false</code>] (default: true)
		</dt><dd>
			If <code>true</code> (which is the default), lombok automatically tries to singularize your identifier name by assuming that it is a common english plural. If <code>false</code>, you must always explicitly specify the singular name, and lombok will generate an error if you don't (useful if you write your code in a language other than english).
		</dd>
	</@f.confKeys>

	<@f.smallPrint>
		<p>
			@Singular support for <code>java.util.NavigableMap/Set</code> only works if you are compiling with JDK1.8 or higher.
		</p><p>
			You cannot manually provide some or all parts of a <code>@Singular</code> node; the code lombok generates is too complex for this. If you want to manually control (part of) the builder code associated with some field or parameter, don't use <code>@Singular</code> and add everything you need manually.
		</p><p>
			The sorted collections (java.util: <code>SortedSet</code>, <code>NavigableSet</code>, <code>SortedMap</code>, <code>NavigableMap</code> and guava: <code>ImmutableSortedSet</code>, <code>ImmutableSortedMap</code>) require that the type argument of the collection has natural order (implements <code>java.util.Comparable</code>). There is no way to pass an explicit <code>Comparator</code> to use in the builder.
		</p><p>
			An <code>ArrayList</code> is used to store added elements as call methods of a <code>@Singular</code> marked field, if the target collection is from the <code>java.util</code> package, <em>even if the collection is a set or map</em>. Because lombok ensures that generated collections are compacted, a new backing instance of a set or map must be constructed anyway, and storing the data as an <code>ArrayList</code> during the build process is more efficient that storing it as a map or set. This behavior is not externally visible, an implementation detail of the current implementation of the <code>java.util</code> recipes for <code>@Singular @Builder</code>.
		</p><p>
			With <code>toBuilder = true</code> applied to methods, any type parameter of the annotated method itself must also show up in the return type.
		</p><p>
			The initializer on a <code>@Builder.Default</code> field is removed and stored in a static method, in order to guarantee that this initializer won't be executed at all if a value is specified in the build. This does mean the initializer cannot refer to <code>this</code>, <code>super</code> or any non-static member. If lombok generates a constructor for you, it'll also initialize this field with the initializer.
		</p><p>
			The generated field in the builder to represent a field with a <code>@Builder.Default</code> set is called <code><em>propertyName</em>$value</code>; an additional boolean field called <code><em>propertyName</em>$set</code> is also generated to track whether it has been set or not. This is an implementation detail; do not write code that interacts with these fields. Instead, invoke the generated builder-setter method if you want to set the property inside a custom method inside the builder.
		</p><p>
			Various well known annotations about nullity cause null checks to be inserted and will be copied to parameter of the builder's 'setter' method. See <a href="/features/GetterSetter">Getter/Setter</a> documentation's small print for more information.
		</p><p>
			You can suppress the generation of the <code>builder()</code> method, for example because you <em>just</em> want the <code>toBuilder()</code> functionality, by using:
			<code>@Builder(builderMethodName = "")</code>. Any warnings about missing <code>@Builder.Default</code> annotations will disappear when you do this, as such warnings
			are not relevant when only using <code>toBuilder()</code> to make builder instances.
		</p><p>
			You can use <code>@Builder</code> for copy constructors: <code>foo.toBuilder().build()</code> makes a shallow clone. Consider suppressing the generating of the
			<code>builder</code> method if you just want this functionality, by using: <code>@Builder(toBuilder = true, builderMethodName = "")</code>.
		</p><p>
			Due to a peculiar way javac processes static imports, trying to do a non-star static import of the static <code>builder()</code> method won't work. Either use a star static import: `import static TypeThatHasABuilder.*;` or don't statically import the <code>builder</code> method.
		</p><p>
			If setting the access level to <code>PROTECTED</code>, all methods generated inside the builder class are actually generated as <code>public</code>; the meaning of the
			<code>protected</code> keyword is different inside the inner class, and the precise behavior that <code>PROTECTED</code> would indicate (access by any source in the same package is allowed, as well as any subclasses <em>from the outer class, marked with <code>@Builder</code></em> is not possible, and marking the inner members <code>public</code> is as close as we can get.
		</p><p>
			If you have configured a nullity annotation flavour via <a href="configuration"><code>lombok.config</code></a> key <code>lombok.addNullAnnotations</code>, any plural-form generated builder methods for <code>@Singular</code> marked properties (these plural form methods take a collection of some sort and add all elements) get a nullity annotation on the parameter. You get a non-null one normally, but if you have configured the behavior on <code>null</code> being passed in as collection to <code>IGNORE</code>, a nullable annotation is generated instead.
		</p>
	</@f.smallPrint>
