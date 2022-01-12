import styles from "../../styles/UploadFile.module.css";
import React from "react";
import { BiImport } from "react-icons/bi";
import axios from "axios";

export default function UploadFile() {
  const [image, setImage] = React.useState({ preview: "", raw: "" });

  const handleChange = (e) => {
    if (e.target.files.length) {
      setImage({
        preview: URL.createObjectURL(e.target.files[0]),
        raw: e.target.files[0],
      });
    }
  };

  const handleUpload = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.set("file", image.raw);
    //formData.append("file", image.raw);
    console.log(formData);

    var bodyFormData = new FormData();
    bodyFormData.append("file", image.raw);
    axios({
      method: "POST",
      url: "http://localhost:8080/multiupload/files",
      data: bodyFormData,
      headers: { "Content-Type": "multipart/form-data" },
    })
      .then(function (response) {
        //handle success
        console.log("true", response);
      })
      .catch(function (response) {
        //handle error
        console.log("false", response);
      });

    setImage({
      preview: "",
      raw: "",
    });
    e.preventDefault();
  };

  return (
    <div className={styles.container}>
      <label htmlFor="upload-button" className={styles.selectButton}>
        {image.preview ? (
          <img src={image.preview} alt="preview" height="100" />
        ) : (
          <>
            <BiImport size={30} />
            <h5 className="text-center">Upload your photo</h5>
          </>
        )}
      </label>
      <input
        type="file"
        id="upload-button"
        style={{ display: "none" }}
        onChange={handleChange}
      />
      <br />
      <button onClick={handleUpload} className={styles.sendButton}>
        Upload
      </button>
    </div>
  );
}
