# `@NoArgsConstructor`, `@RequiredArgsConstructor`, `@AllArgsConstructor`

* goal
  * how to generate constructors

## Overview

* `@NoArgsConstructor`
  * generate constructor WITHOUT arguments
    * ❌if this is NOT possible == compiler error ❌
      * Reason: 🧠there are final fields 🧠
      * Solution: 👀set `force = true` (== `@NoArgsConstructor(force = true)`) -> ALL final fields -- are initialized with -- `0` OR `false` OR `null` 👀
    * 👀if fields have constraints (_Example:_`@NonNull` fields) -> 
      * NO check is generated   == omit constraint
      * | initialize later those fields, 
        * constraints will generally fulfill 👀
  * use cases
    * SOME java constructs
      * _Example:_ hibernate and Service Provider Interface
    * \+ 
      * `@Data` OR
      * OTHER constructor generating annotations

* `@RequiredArgsConstructor`
  * generate constructor /
    * 1 argument / final or non-null field
      * -> argument -- will be assigned to the -- field
  * TODO:

* `@AllArgsConstructor`
  * generate constructor /
    * 1 argument / EVERY field
      * -> argument -- will be assigned to the -- field

              <p>
                  <code>@RequiredArgsConstructor</code> generates a constructor with 1 parameter for each field that requires special handling. All non-initialized <code>final</code> fields get a parameter, as well as any fields that are marked as <code>@NonNull</code> that aren't initialized where they are declared. For those fields marked with <code>@NonNull</code>, an explicit null check is also generated. The constructor will throw a <code>NullPointerException</code> if any of the parameters intended for the fields marked with <code>@NonNull</code> contain <code>null</code>. The order of the parameters match the order in which the fields appear in your class.
              </p><p>
                  <code>@AllArgsConstructor</code> generates a constructor with 1 parameter for each field in your class. Fields marked with <code>@NonNull</code> result in null checks on those parameters.
              </p><p>
                  Each of these annotations allows an alternate form, where the generated constructor is always private, and an additional static factory method that wraps around the private constructor is generated. This mode is enabled by supplying the <code>staticName</code> value for the annotation, like so: <code>@RequiredArgsConstructor(staticName="of")</code>. Such a static factory method will infer generics, unlike a normal constructor. This means your API users get write <code>MapEntry.of("foo", 5)</code> instead of the much longer <code>new MapEntry&lt;String, Integer&gt;("foo", 5)</code>.
              </p><p>
                  To put annotations on the generated constructor, you can use <code>onConstructor=@__({@AnnotationsHere})</code>, but be careful; this is an experimental feature. For more details see the documentation on the <a href="/features/experimental/onX">onX</a> feature.
              </p><p>
                  Static fields are skipped by these annotations.
              </p><p>
                  Unlike most other lombok annotations, the existence of an explicit constructor does not stop these annotations from generating their own constructor. This means you can write your own specialized constructor, and let lombok generate the boilerplate ones as well. If a conflict arises (one of your constructors ends up with the same signature as one that lombok generates), a compiler error will occur.
              </p>

## Supported configuration keys
              <dt>
                  <code>lombok.anyConstructor.addConstructorProperties</code> = [<code>true</code> | <code>false</code>] (default: <code>false</code>)
              </dt><dd>
                  If set to <code>true</code>, then lombok will add a <code>@java.beans.ConstructorProperties</code> to generated constructors.
              </dd><dt>
                  <code>lombok.</code>[<code>allArgsConstructor</code>|<code>requiredArgsConstructor</code>|<code>noArgsConstructor</code>]<code>.flagUsage</code> = [<code>warning</code> | <code>error</code>] (default: not set)
              </dt><dd>
                  Lombok will flag any usage of the relevant annotation (<code>@AllArgsConstructor</code>, <code>@RequiredArgsConstructor</code> or <code>@NoArgsConstructor</code>) as a warning or error if configured.
              </dd><dt>
                  <code>lombok.anyConstructor.flagUsage</code> = [<code>warning</code> | <code>error</code>] (default: not set)
              </dt><dd>
                  Lombok will flag any usage of any of the 3 constructor-generating annotations as a warning or error if configured.
              </dd><dt>
                  <code>lombok.copyableAnnotations</code> = [<em>A list of fully qualified types</em>] (default: empty list)
              </dt><dd>
                  Lombok will copy any of these annotations from the field to the constructor parameter, the setter parameter, and the getter method. Note that lombok ships with a bunch of annotations 'out of the box' which are known to be copyable: All popular nullable/nonnull annotations.
              </dd><dt>
                  <code>lombok.noArgsConstructor.extraPrivate</code> = [<code>true</code> | <code>false</code>] (default: false)
              </dt><dd>
                  If <code>true</code>, lombok will generate a private no-args constructor for any <code>@Value</code> or <code>@Data</code> annotated class, which sets all fields to default values (null / 0 / false).
              </dd>
          </@f.confKeys>

          <@f.smallPrint>
              <p>
                  Even if a field is explicitly initialized with <code>null</code>, lombok will consider the requirement to avoid null as fulfilled, and will <em>NOT</em> consider the field as a 'required' argument. The assumption is that if you explicitly assign <code>null</code> to a field that you've also marked as <code>@NonNull</code> signals you must know what you're doing.
              </p><p>
                  The <code>@java.beans.ConstructorProperties</code> annotation is never generated for a constructor with no arguments. This also explains why <code>@NoArgsConstructor</code> lacks the <code>suppressConstructorProperties</code> annotation method. The generated static factory methods also do not get <code>@ConstructorProperties</code>, as this annotation can only be added to real constructors.
              </p><p>
                  <code>@XArgsConstructor</code> can also be used on an enum definition. The generated constructor will always be private, because non-private constructors aren't legal in enums. You don't have to specify <code>AccessLevel.PRIVATE</code>.
              </p><p>
                  Various well known annotations about nullity cause null checks to be inserted and will be copied to the parameter. See <a href="/features/GetterSetter">Getter/Setter</a> documentation's small print for more information.
              </p><p>
                  The <code>flagUsage</code> configuration keys do not trigger when a constructor is generated by <code>@Data</code>, <code>@Value</code> or any other lombok annotation.
              </p>
          </@f.smallPrint>
      </@f.scaffold>
