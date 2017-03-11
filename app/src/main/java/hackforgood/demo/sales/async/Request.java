package hackforgood.demo.sales.async;

import com.google.gson.JsonElement;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file Request.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public interface Request {

    @GET("/{url}")
    JsonElement startGetService(@Path(value="url", encode=false) String url);
}
