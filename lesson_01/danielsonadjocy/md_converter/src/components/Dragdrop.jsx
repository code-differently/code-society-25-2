import React, { useRef, useState, useContext } from 'react';
import PropTypes from 'prop-types';
import "./Dragdrop.css";
import { FaUpload, FaDownload} from 'react-icons/fa';




const DragDrop = props => {

    const wrapperRef = useRef(null);

    const [file, setFile] = useState(null);

    const [fileDropped, setFileDropped] = useState(false);
    
    const [fileName,setFileName] = useState("");

    const [downloadURL, setDownloadURL] = useState(null)

    const onDragEnter = () => wrapperRef.current.classList.add('dragover');
    const onDragLeave = () => wrapperRef.current.classList.remove('dragover');
    const onDrop = () => wrapperRef.current.classList.remove('dragover');

    const onFileDrop = (e) => {
        const newFile = e.target.files[0];
        if (newFile) {
            setFileDropped(true);
            setFile(newFile);
            //setFileName(newFile.name); // File name should actually be the HTML file
            
            handleFileUpload(newFile);
            //props.onFileChange([newFile]); Should need this later
        }
    }

    const handleFileUpload = async (file) => {
        if (!file){
            return;
        }
        console.log(file.name);
        const formData = new FormData();
        formData.append("markdown", file);
        try {
            const response = await fetch('http://localhost:5001/convert', {// REMEMBER THIS
            method: "POST",
            body: formData
            });

            if (!response.ok){
                throw new Error("Conversion failed");
            }
            
            console.log("Sending succeed!!")// to be deleted

            const blob = await response.blob();

            const url = window.URL.createObjectURL(blob);

            setDownloadURL(url);
            setFileName(file.name.replace(/\.md$/, ".html"));
        }

        catch(error){
            console.error("UPLOAD FAILING!!");
        }


    }

    // TODO: add download function
    const handleDownload = () =>{
        if (!fileName){
            return;
        }
        const a = document.createElement("a");
        a.href = downloadURL;
        a.download = fileName;
        a.click();
    }


    /*
    const fileRemove = (file) => {
        const updatedList = [...fileList];
        updatedList.splice(fileList.indexOf(file), 1);
        setFileList(updatedList);
        props.onFileChange(updatedList);
    }*/

    return (
        <>
            <div
                ref={wrapperRef}
                className="drop-file-input"
                onDragEnter={onDragEnter}
                onDragLeave={onDragLeave}
                onDrop={onDrop}
            >
                <div className="drop-file-input__label">
                    <FaUpload style={{ fontSize: '10rem'}}/>
                    <p>Drag & Drop your files here</p>
                </div>
                <input type="file" value="" onChange={onFileDrop} className='upload-download'/>
            </div>

            {/*Download button*/
                fileDropped &&
            <div
                ref={wrapperRef}
                className="drop-file-input"
                onDragEnter={onDragEnter}
                onDragLeave={onDragLeave}
                onDrop={onDrop}
            >
                <div className="drop-file-input__label">
                    <FaDownload style={{ fontSize: '10rem'}}/>
                    <p>{fileName}</p>
                </div>
                <button className='upload-download' onClick={handleDownload}></button>
            </div>
            }
        </>
    );
}

DragDrop.propTypes = {
    onFileChange: PropTypes.func
}

export default DragDrop;