import http from 'k6/http';
import { check, sleep } from 'k6';

var host = 'https://api-analytics.buildstaging.com/rest/databox/run-by-identifier/pagesCount'

var hostAstrobox = 'https://api-astrobox.buildstaging.com/v1/executor/reactive/by-id/'

// export const options = {
//    vus: 10,
//    duration: '30s',
//  };

function verifyResponse(response){
    if(response.status == 200 || response.status == 201 || response.status == 204){
        console.log('request successfull !!!')
        //console.log(response.body)
    }
    else{
        console.log('request failed: response code '+ response.status + " - " + response.error)
    }
}

export default function () {

    const params = {
        headers: {
            'Content-Type': 'application/json',
            'User-Agent': 'k6',
            'Authorization': 'Bearer H4sIAAAAAAAAAJVUXZOqOBD9RW5BhPHyCEIUxoQBkkB4sRRUPv0YRCC%2FfoOzdevW7tM%2BUCFJp8%2FpPt19mrziuMlKv%2FQiKlwVl27nXkM9W7sfbn1P2Noz%2FjpNnpoBNiVAL44xnY3LNPF6nnj1cXrK%2F7DAtiuwnZqHjaEcYzjla706AqXnV3eF47DicaAhO3hykQ1oUhTcwnpHaiWN0TOtwhrZ7pi2CKT2ZemWQ5kB%2FMo2EqvpyqxlRbqeieXTIQkbt1E6ty1e%2BVp983KrW%2FmDz1cswU1WmgqqkIKrboUmbUKV08u9tqtcxbef%2F4t7PvurggmJAPjCkXZIQcIckOADss1VcPWKDDSdL54opLlFoAFJE3oM1qsgCe95%2B2sVUm3FGHZ8IN8COvk2GnbE1DGgT0Rgg9dqmZJA3RF3kj4HXgWDH3ONk26UuBIzGLCol1ggfVeZPRaOzCVSsZDnlTMgkuk%2B6QbJb4lsDlAl4ydU2mSrcGN8klhTMBhbHBeH40bXj9u8zJmVHJT8EBMM2VWy%2Byd%2FiHANVXT07YtARMZpPyvfzkteIRVFaiM5iB3BNbbNJ2%2FphCe1QABNvA0AAsGI2mCVbz01Jd3KFzJvdiDX7v39W5tZ5zQpBqnfiIUJZIxCaqXu1l6RJ%2BHtuPTu%2BaZpssn9iCePUAdGkTLCRGUkZG4p7UAa63Uah69sLtwW39IYfx%2BXTEkArE8x62cMvmxmzYW8U7O2UU7MkHUHn%2FMdcZgVQoPSGtqRwqL5LHLkHnpfiZJGIWU4kul5c900ItuMxSmasWDP41GX%2FKpMNQAq%2F3NWpFs0c0xC6nmkhr99EwojBg0UsRQmigeZ48x2AXEgDWsjIswgEc19xsx3jHk8yv7RXwnwmnzztpX1ORa8NSb%2BO%2FY%2FsKf3XvnJjS6jmHuHCZmDM2%2FlO9DNPoasNWqZ55%2F3tQoJ9SBtsBsx6xzUjAbvOPHrmFh%2F4DR93hp3DuibM62bOKA4TZQwCP7Eub71CSJWQNkT65CF1k%2Ff3pVDJIdLdVli0tTpJq12MS6RqJ%2BytwQvVdnLl2kXwxZt5FwQmeoTq%2BU%2F%2BnYHqWkOjXs6D6iWAt6GDQe43JFMYHKRPmpd1mQpa1L2GBo5cAQn5ohjNKaRHGY8o9HnId0v8jS%2FiOe2X3%2FeDaXXP9xDqyn36680QoNpqmJ%2FNQeBjXXLsyvWHjCxro%2BD8fH9wtayqJ7mgkexv9bVk3Xy122iB8m5RB35WFS6zsad8j09sxtPxsvtQk0H%2BrsC1I8%2B2Rz0%2FZecDaaVWEvtGNfBZf94FEDrF4k7bPfPzZdbl7Cltr%2F7rm7rM2dAAyw%2BewuR%2Brj7flwedcsEOofLy%2Fcy3C7s6MiCj9s5g5%2B193UjuX1Y5i%2Bq%2BquHfZrIDh3S6fbVLw0tInfL4tut8I%2FjA%2BIuzj6L5k7DSWdN%2FEj2o22npBEWKpSKnFO1yxbN8tf%2BonjXGG0cqIl8a2pGr9z6D02pakxffritFv3wN%2F%2F7tCZIBgAA',
        },
    };

    const payload = JSON.stringify({
            "query":"6f5665bf-a485-4de9-bba8-4cfdd2cb829a",
            "parameters":{
                "beginDate":"2023/11/01",
                "endDate": "2023/11/26",
                "currency": "BRL",
                "clickMode": "ANY",
                "leadMode": "ANY"
            }
    });

    let res =  http.get(host, params);

    //let res =  http.post(hostAstrobox, payload, params);

    check(res, {
        'status was 200': r => r.status == 200
    });

    sleep(2);
    
    verifyResponse(res);
}