# to-jsonapi

Do only one thing, formatting entities to jsonapi format. No extra dependencies, except java sdk. It results a hashmap which you can use any json library to convert to json format.

## examples
```java
@JsonapiResource(type="hello")
public class MyUserWithAnnotation extends BaseModel {
	
	@JsonapiField(name = "un")
	private String username;
	
	private Date birthDay;
	
	@JsonapiFieldIgnore
	private String ignored;
	
	private long uniqueNumber;
	
	@JsonapiRelation(targetResourceClass=MyRole.class, relationType=JsonapiRelationType.ITERABLE)
	private List<MyRole> roles;
```



```java

Pager pager = new OffsetlimitPager("offset", "limit");
JsonapiDocumentBuilder jdb = new JsonapiDocumentBuilder(pager);
JsonApiDocument jad = jdb.buildListResource(rss, 100, "http://www.abc.com/resources/?page[offset]=40&page[limit]=10");
Map<String, Object> map = jad.asMap();

```

```json
{
  "data":[
    {
      "id":"1",
      "type":"hello",
      "attributes":{
        "un":"username",
        "birthDay":"20180305155312+0800",
        "uniqueNumber":1
      },
      "relationships":{
        "roles":{
          "links":{
            "self":"http://www.abc.com/hello/1/relationships/roles",
            "related":"http://www.abc.com/hello/1/roles"
          }
        }
      },
      "links":{
        "self":"http://www.abc.com/hello/1"
      }
    },
    {
      "id":"2",
      "type":"hello",
      "attributes":{
        "un":"username",
        "birthDay":"20180305155312+0800",
        "uniqueNumber":1
      },
      "relationships":{
        "roles":{
          "links":{
            "self":"http://www.abc.com/hello/2/relationships/roles",
            "related":"http://www.abc.com/hello/2/roles"
          }
        }
      },
      "links":{
        "self":"http://www.abc.com/hello/2"
      }
    },
    {
      "id":"3",
      "type":"hello",
      "attributes":{
        "un":"username",
        "birthDay":"20180305155312+0800",
        "uniqueNumber":1
      },
      "relationships":{
        "roles":{
          "links":{
            "self":"http://www.abc.com/hello/3/relationships/roles",
            "related":"http://www.abc.com/hello/3/roles"
          }
        }
      },
      "links":{
        "self":"http://www.abc.com/hello/3"
      }
    }
  ],
  "meta":{
    "totalResourceCount":100
  },
  "links":{
    "first":"http://www.abc.com/resources/?page[limit]=10",
    "last":"http://www.abc.com/resources/?page[offset]=90&page[limit]=10",
    "prev":"http://www.abc.com/resources/?page[offset]=30&page[limit]=10",
    "next":"http://www.abc.com/resources/?page[offset]=50&page[limit]=10"
  }
}

```

## questions

1. Why use annotation in stead of name in requesting URL to describe resource name?
You can only get top level resource name from URL, what about relationships? So use annotations. If the resource name in URL conflicts with annotation the result was shown in above code. use the URL passed in for pagination links and use annotation name for internal links.
