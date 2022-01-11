import styles from "../../styles/UploadFile.module.css";
import React from "react";
import { BiImport } from "react-icons/bi";
import { useForm } from "react-hook-form";

export default function UploadFile() {
  const { register, handleSubmit } = useForm();
  const [fileName, setFileName] = React.useState();

  const onSubmit = (data) => {
    const file = data;
    console.log("Uploaded a file");
    console.log(data.image[0].name);
  };

  return (
    <div className={styles.container}>
      <div style={{ display: "flex", flexDirection: "row", height: "50px" }}>
        <form
          onSubmit={handleSubmit(onSubmit)}
          style={{ display: "flex", flexDirection: "row" }}
        >
          <label htmlFor="formId" className={styles.selectButton}>
            <span style={{ margin: "0px 5px" }}>
              <BiImport size={30} />
            </span>
            <input
              name="image"
              type="file"
              id="formId"
              accept="image/*"
              {...register("image", { required: true })}
            />
          </label>
          <div className={styles.buttonContainer}>
            <input className={styles.sendButton} type="submit" />
          </div>
        </form>
      </div>
    </div>
  );
}
