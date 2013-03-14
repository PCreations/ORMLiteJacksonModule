package fr.pcreations.labs.RESTDroid.modules.ORMLiteJacksonModule;


import java.sql.SQLException;

import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import fr.pcreations.labs.RESTDroid.core.Persistable;
import fr.pcreations.labs.RESTDroid.core.PersistableFactory;
import fr.pcreations.labs.RESTDroid.core.ResourceRepresentation;
import fr.pcreations.labs.RESTDroid.core.RestService;

public class ORMLitePersistableFactory extends PersistableFactory {

	private OrmLiteSqliteOpenHelper mHelper;
    
    public ORMLitePersistableFactory(OrmLiteSqliteOpenHelper helper) {
            mHelper = helper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P extends Persistable<T>, T extends ResourceRepresentation<?>> P getPersistable(
                    Class<T> clazz) {
    	Log.i(RestService.TAG, "getDao OF : " + clazz.getSimpleName());
		P persistable;
		
		if(mPersistables.containsKey(clazz)) {
			return (P) mPersistables.get(clazz);
		}
		
		try {
			persistable = (P) mHelper.getDao(clazz);
			mPersistables.put(clazz, persistable);
			return persistable;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
    }
	
}
