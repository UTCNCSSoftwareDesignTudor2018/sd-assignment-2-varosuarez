package data.entity;

public class EntityFactory {
	
	public class ShapeFactory {

	public Entity getEntity(String string) {
		if(string == null){
	         return null;
	      }		
	      if(string.equalsIgnoreCase("student")){
	         return new Student();
	         
	      } else if(string.equalsIgnoreCase("teacher")){
	         return new Teacher();
	         
	      } else if(string.equalsIgnoreCase("course")){
	         return new Course();
	      }
	      
	      return null;
	   }
	}

}
