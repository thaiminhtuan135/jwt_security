import React, { useState, useEffect, useRef } from 'react';

import { Loader } from '@googlemaps/js-api-loader';
import {API_KEY, LANGUAGE, LIBRARIES, VERSION} from "../../../config";

const Map: React.FC = () => {
    const [map, setMap] = useState<google.maps.Map>();
    const mapRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        const loader = new Loader({
            apiKey: API_KEY,
            language: LANGUAGE,
            // @ts-ignore
            libraries: LIBRARIES,
            version: VERSION,
        });

        loader.load().then(() => {
            if (mapRef.current) {
                const map = new google.maps.Map(mapRef.current, {
                    center: { lat: 0, lng: 0 },
                    zoom: 3,
                });
                setMap(map);
            }
        });
    }, []);

    return (
        <div
            ref={mapRef}
            style={{ height: '500px', width: '100%' }}
        />
    );
};

export default Map;
