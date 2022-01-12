import styles from "../../styles/Objectlist.module.css";
import React from "react";
import axios from "axios";
import { BsFillImageFill } from "react-icons/bs";

export default function ObjectList() {
  const [objects, setObjects] = React.useState([]);

  React.useEffect(() => {
    axios.get("http://localhost:8080/minio/getAll").then((response) => {
      setObjects(response.data);
    });
  }, []);

  return (
    <div className={styles.container}>
      {objects.map((object, index) => (
        <div key={object} className={styles.objectCard}>
          <span
            style={{
              margin: "0px 5px",
              display: "flex",
              alignItems: "center",
            }}
          >
            {index + 1} |
            <BsFillImageFill />
          </span>

          <p className={styles.objetName}>
            <a href={`http://localhost:8080/minio/${object}`} target="_blank">
              {object}
            </a>
          </p>
        </div>
      ))}
    </div>
  );
}
