import styles from "../../styles/UploadFile.module.css";
import React from "react";
import { BiImport } from "react-icons/bi";

export default function UploadFile() {
  return (
    <div className={styles.container}>
      <label htmlFor="formId" className={styles.selectButton}>
        <input name="" type="file" id="formId" hidden />
        <span style={{ margin: "0px 5px" }}>
          <BiImport size={30} />
        </span>
        <b>Select Image</b>
      </label>
    </div>
  );
}
