import styles from "../../styles/Objectlist.module.css";
import React from "react";
import axios from "axios";
import { BsFillImageFill } from "react-icons/bs";

export default function ObjectList({ object, index }) {
  const [disable, setDisable] = React.useState(false);

  const deleteObject = (object) => {
    axios.delete(`http://localhost:8080/minio/${object}`);
    setDisable(true);
  };

  return (
    <>
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

        <p className={disable ? styles.objectNameDisable : styles.objectName}>
          <a
            href={`http://localhost:8080/minio/${object}`}
            target="_blank"
            className={disable ? styles.disableObjectName : styles.objectName}
          >
            {object}
          </a>
        </p>
        <button
          className={styles.deleteButton}
          onClick={() => deleteObject(object)}
        >
          DELETE
        </button>
      </div>
      <hr style={{ width: "100%" }} />
    </>
  );
}
