f (${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import com.charpixel.baseandroidproject.common.BaseResponseData;
import com.charpixel.baseandroidproject.common.NoDataResponse;
import com.charpixel.baseandroidproject.common.UsecaseBase;
import com.charpixel.medrexauserapp.repositories.MedrexaRepositories;

import javax.inject.Inject;

import io.reactivex.Observable;

//created by vishal gupta

public class ${NAME}Usecase extends UsecaseBase<BaseResponseData<NoDataResponse>> {

    private final String TAG = this.getClass().getSimpleName();

    ProjectApiRepository apiRepository;
    
        Request request = new Request();

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Inject
    public ${NAME}Usecase(ProjectApiRepository apiRepository) {
        this.apiRepository = apiRepository;

    }


    @Override
    public Observable<BaseResponseData<NoDataResponse>> buildObservable() {

        return null;
    }

    public static class Request {


    }


}
