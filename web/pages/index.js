import Head from "next/head";
import styles from "../styles/Home.module.css";
import React from "react";
import axios from "axios";
import ObjectList from "./components/objectList";
import UploadFile from "./components/uploadFile";

export default function Home() {
  const [objects, setObjects] = React.useState([]);

  React.useEffect(() => {
    axios.get("http://localhost:8080/minio/getAll").then((response) => {
      setObjects(response.data);
    });
  }, []);

  return (
    <div className={styles.container}>
      <Head>
        <title>Minio Storage</title>
        <meta name="description" content="Generated by create next app" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <h1 className={styles.title}>
          Welcome to <a>MinIO Storage</a>
        </h1>
        <UploadFile />
        <p className={styles.description}>
          <b>All stored images</b>
        </p>

        <div className={styles.objectContainer}>
          {objects.map((object, index) => (
            <>
              <ObjectList object={object} index={index} />
            </>
          ))}
        </div>
      </main>

      <footer className={styles.footer}>MinIO & Quarkus POC</footer>
    </div>
  );
}
