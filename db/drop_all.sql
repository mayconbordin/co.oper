select 'drop '||object_type||' '|| object_name|| DECODE(OBJECT_TYPE,'TABLE',' CASCADE CONSTRAINTS PURGE;',';') 
from user_objects 
order by object_type desc; 
