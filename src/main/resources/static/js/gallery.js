async function isAvailableURL(urlname)
{
    const result = await axios.get(`/galleryRest/availableURLName/${urlname}`)
    return result.data
}

async function isAvailableTitle(title)
{
    const result = await axios.get(`/galleryRest/availableTitle/${title}`)
    return result.data
}

async function getGalleryForAuto(keyword)
{
    const result = await axios.get(`/galleryRest/galleryAutoList/${keyword}`)
    return result.data
}