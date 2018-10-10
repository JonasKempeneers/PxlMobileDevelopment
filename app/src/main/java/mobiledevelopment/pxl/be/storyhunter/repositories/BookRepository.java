package mobiledevelopment.pxl.be.storyhunter.repositories;

import java.util.List;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import retrofit2.Call;
import retrofit2.http.GET;


public class BookRepository {

    private String url = "";
    private JSONArray booksArray = null;

//    public JSONArray getAllBooks(){
//        url = "http://my-json-server.typicode.com/JonasKempeneers/FakeApi/books";
//
//        JsonArrayRequest jsonOArrayRequest = new JsonArrayRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        booksArray = response;
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//                    }
//                });
//
//        return booksArray;
//    }


}
